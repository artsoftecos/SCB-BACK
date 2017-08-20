package com.artsoft.scb.model.bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class DocumentLocalService implements IDocumentService {
	
	private String domainName;
	private boolean isFileStorageAsync;	
	private static TransferManager tx;
	
	public DocumentLocalService(@Value("#{ @environment['Document.AsyncUpload'] ?: 0 }")boolean isAsync) {
		this.isFileStorageAsync = isAsync;
	}
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public void upload(File file, String fileName, String folderName) throws AmazonServiceException, AmazonClientException, InterruptedException {
		String pathfile = folderName + "/"+ fileName;
		PutObjectRequest request = new PutObjectRequest(domainName, pathfile, file);
		Upload upload = tx.upload(request);
		if (!this.isFileStorageAsync) {
			upload.waitForUploadResult();			
		} 	
	}
	
	public File get(String fileName, String folderName, String extension) throws Exception {
		File file = null;
		try {			
			file = File.createTempFile(fileName, extension);	
			String pathFile = folderName + "/" + fileName + extension;
			GetObjectRequest request = new GetObjectRequest(domainName, pathFile);
			Download download = tx.download(request, file);
			download.waitForCompletion();			

			boolean success = file.exists() && file.canRead();
			if (!success) {				
				throw new Exception("It was not possible to find the requested file, exists: " + file.exists()
						+ ", possible to read: " + file.canRead());
			}
			return file;			
		} catch (AmazonServiceException e) {			
			throw new AmazonServiceException("error: " + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (FileNotFoundException e) {			
			throw new FileNotFoundException("error: " + e.getMessage() + e.toString());
		} catch (IOException e) {			
			throw new IOException("error: " + e.getMessage() + e.toString());
		}
	}
	
	public ArrayList<String> getFiles(String folderName) throws Exception {
		ArrayList<String> Files = new ArrayList<String>();
		try {
			ObjectListing listing = tx.getAmazonS3Client().listObjects(domainName, folderName+"/");
			for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
				Files.add(objectSummary.getKey());
			}
		}
		catch (AmazonServiceException e) {
			throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception("error : " + e.getMessage(), e);
		}
		return Files;
	}

	public void delete(String key) throws Exception {		
		try {
			tx.getAmazonS3Client().deleteObject(domainName,key);
		}
		catch (AmazonServiceException e) {
			throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception("error : " + e.getMessage(), e);
		}
	}
	
	public void createBucket() {
		if (tx.getAmazonS3Client().doesBucketExist(domainName) == false) {
			tx.getAmazonS3Client().createBucket(domainName);
		}
	}

	public ArrayList<String> getBuckets() throws Exception {
		ArrayList<String> bucketsAndFiles = new ArrayList<String>();
		try {
			for (Bucket bucket : tx.getAmazonS3Client().listBuckets()) {
				bucketsAndFiles.add(bucket.getName());
			}

			ObjectListing objectListing = tx.getAmazonS3Client().listObjects(new ListObjectsRequest().withBucketName(domainName));
						
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				bucketsAndFiles.add(objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
			}
		} catch (AmazonServiceException e) {
			throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception("error : " + e.getMessage(), e);
		}
		return bucketsAndFiles;
	}
}
