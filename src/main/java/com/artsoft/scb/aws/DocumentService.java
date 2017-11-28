package com.artsoft.scb.aws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;

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
import com.artsoft.scb.model.bll.ExceptionService;
import com.artsoft.scb.model.bll.interfaces.IDocumentService;

public class DocumentService extends ExceptionService implements IDocumentService {
	
	private String domainName;
	private boolean isFileStorageAsync;	
	private static TransferManager tx;
		
	public DocumentService( @Value("#{ @environment['Document.AsyncUpload'] ?: 0 }")boolean isAsync) throws JSONException {
		this.isFileStorageAsync = isAsync;
		
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			tx = TransferManagerBuilder.standard().withS3Client(s3Client).build();			
		} catch (Exception e) {	
			throwException("Response","Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (~/.aws/credentials), and is in valid format." + e.toString());
			/*throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (~/.aws/credentials), and is in valid format.", e);*/
		}
	}
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public void upload(File file, String fileName, String folderName, HttpServletRequest request) throws AmazonServiceException, AmazonClientException, InterruptedException, Exception {
		String pathfile = folderName + "/"+ fileName;
		PutObjectRequest putRequest = new PutObjectRequest(domainName, pathfile, file);
try{
	Upload upload = tx.upload(putRequest);
	if (!this.isFileStorageAsync) {
		upload.waitForUploadResult();			
	} 	
}catch (Exception e) {
	throwException("Response", e.toString());
}
		
	}
	
	
	public File get(String fileName, String folderName, HttpServletRequest request) throws Exception {
		File file = null;
		try {			
			String name = FilenameUtils.getBaseName(fileName);		
			String extension = FilenameUtils.getExtension(fileName);
			
			file = File.createTempFile(name, "."+extension);	
			String pathFile = folderName + "/" + fileName;
			GetObjectRequest getRequest = new GetObjectRequest(domainName, pathFile);
			Download download = tx.download(getRequest, file);
			download.waitForCompletion();			

			boolean success = file.exists() && file.canRead();
			if (!success) {		
				throwException("Response", "It was not possible to find the requested file, exists: " + file.exists()
						+ ", possible to read: " + file.canRead());
//				throw new Exception("It was not possible to find the requested file, exists: " + file.exists()
//						+ ", possible to read: " + file.canRead());
			}		
		} catch (AmazonServiceException e) {
			throwException("Response", e.getErrorMessage() + ", " + e.getMessage());
//			throw new AmazonServiceException("error: " + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (FileNotFoundException e) {			
			throwException("Response", e.getMessage() + e.toString());
			//throw new FileNotFoundException("error: " + e.getMessage() + e.toString());
		} catch (IOException e) {
			throwException("Response", e.getMessage() + e.toString());
			//throw new IOException("error: " + e.getMessage() + e.toString());
		}
		return file;	
	}
	
	public ArrayList<String> getFiles(String folderName, HttpServletRequest request) throws Exception {
		ArrayList<String> Files = new ArrayList<String>();
		try {
			ObjectListing listing = tx.getAmazonS3Client().listObjects(domainName, folderName+"/");
			for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
				Files.add(objectSummary.getKey());
			}
		}
		catch (AmazonServiceException e) {
			throwException("Response", e.getErrorMessage() + ", " + e.getMessage());
			//throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throwException("Response", e.getMessage());			
		}
		return Files;
	}

	public void delete(String folderName, String fileName, HttpServletRequest request ) throws Exception {		
		try {
			String file = folderName + "/" + fileName;
			tx.getAmazonS3Client().deleteObject(domainName,file);
		}
		catch (AmazonServiceException e) {
			throwException("Response", e.getErrorMessage() + ", " + e.getMessage());			
			//throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throwException("Response", e.getMessage());		
			//throw new Exception("error : " + e.getMessage(), e);
		}
	}
	
	private void createBucket() {
			tx.getAmazonS3Client().createBucket(domainName);
	}

	private ArrayList<String> getBuckets() throws Exception {
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
			throwException("Response", e.getErrorMessage() + ", " + e.getMessage());		
			//throw new AmazonServiceException("error :" + e.getErrorMessage() + ", " + e.getMessage(), e);
		} catch (Exception e) {
			throwException("Response", e.getMessage());
			//throw new Exception("error : " + e.getMessage(), e);
		}
		return bucketsAndFiles;
	}
}
