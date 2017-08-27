package com.artsoft.scb.model.bll;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.artsoft.scb.model.bll.interfaces.IDocumentService;
import com.artsoft.scb.model.dao.ApplicantDocumentRepository;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantDocument;

@RunWith(SpringRunner.class)
public class ApplicantDocumentServiceTest {
	
	@TestConfiguration
	static class ApplicantDocumentServiceTestContextConfiguration {

		@Mock
		IDocumentService documentService;

		@Bean
		public ApplicantDocumentService applicantDocumentService() {
			MockitoAnnotations.initMocks(this);
			Mockito.doNothing().when(documentService).setDomainName("Mock");

			ApplicantDocumentService app = new ApplicantDocumentService("Mock", documentService);
			return app;
		}

		@Bean
		public static PropertySourcesPlaceholderConfigurer properties() {

			final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			final Properties properties = new Properties();
			properties.setProperty("ApplicantDocument.allowedFileFormats", "pdf, jpg");
			properties.setProperty("ApplicantDocument.amountAllowedFiles", "1");
			properties.setProperty("ApplicantDocument.allowedFileSize", "1");
			pspc.setProperties(properties);
			return pspc;
		}
	}

	@MockBean
	private IDocumentService documentService;
	
	@MockBean
	private ApplicantDocumentRepository applicantDocumentRepository;

	/**
	 * Repository of applicant.
	 */
	
	@MockBean
	private ApplicantRepository applicantRepository;

	@Autowired
	private ApplicantDocumentService applicantDocumentService;

	@Test
	public void uploadDocument_whenNoFile_thenException() {
		MultipartFile file = null;
		String name = "name";
		String email = "email@hola.com";
		HttpServletRequest request = null;

		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void uploadDocument_whenNoName_thenException() {
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "pdf", "bar".getBytes());		
		String name = "";
		String email = "email@hola.com";
		HttpServletRequest request = null;

		Applicant applicant = new Applicant();
		applicant.setEmail(email);
		Mockito.when(applicantRepository.findByEmail(email)).thenReturn(applicant);
		
		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	@Test
	public void uploadDocument_whenNoEmail_thenException() {
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "pdf", "bar".getBytes());		
		String name = "testing";
		String email = "";
		HttpServletRequest request = null;

		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void uploadDocument_whenNoFoundApplicant_thenException() {
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "pdf", "bar".getBytes());		
		String name = "testing";
		String email = "pepito@email.com";
		HttpServletRequest request = null;

		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void uploadDocument_whenNoValidFormatFile_thenException() {
		MockMultipartFile file = new MockMultipartFile("file", "file.txt", "txt", "bar".getBytes());		
		String name = "testing";
		String email = "pepito@email.com";
		HttpServletRequest request = null;

		Applicant applicant = new Applicant();
		applicant.setEmail(email);
		Mockito.when(applicantRepository.findByEmail(email)).thenReturn(applicant);
		
		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void uploadDocument_whenMaximumFiles_thenException() {
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "pdf", "bar".getBytes());
		String name = "testing";
		String email = "pepito@email.com";
		HttpServletRequest request = null;

		Applicant applicant = new Applicant();
		applicant.setEmail(email);
		
		ApplicantDocument applicantDocumentOne = new ApplicantDocument();
		applicantDocumentOne.setApplicant(applicant);
		applicantDocumentOne.setFileName("otroFile.pdf");
		applicantDocumentOne.setName("cedula");
		
		List<ApplicantDocument> documents = new ArrayList<ApplicantDocument>();
		documents.add(applicantDocumentOne);
		
		Mockito.when(applicantRepository.findByEmail(email)).thenReturn(applicant);
		Mockito.when(applicantDocumentRepository.findByApplicant(applicant))
		.thenReturn(documents);
				
		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void uploadDocument_whenValid_thenSuccess() {
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "pdf", "bar".getBytes());		
		String name = "name";
		String email = "email@hola.com";
		HttpServletRequest request = null;

		Applicant applicant = new Applicant();
		applicant.setEmail(email);
		ApplicantDocument applicantDocument = new ApplicantDocument();
		applicantDocument.setApplicant(applicant);
		applicantDocument.setFileName(file.getName());
		applicantDocument.setName(name);

		Mockito.when(applicantRepository.findByEmail(email)).thenReturn(applicant);
		Mockito.when(applicantDocumentRepository.findByApplicant(applicant))
				.thenReturn(new ArrayList<ApplicantDocument>());
		Mockito.when(applicantDocumentRepository.save(any(ApplicantDocument.class))).thenReturn(applicantDocument);

		try {
			applicantDocumentService.uploadDocument(file, name, email, request);
		} catch (Exception e) {
			fail();
		}
	}
	
}
