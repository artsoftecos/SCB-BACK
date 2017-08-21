package com.artsoft.scb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.artsoft.scb.model.bll.ApplicantDocumentService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicantDocumentController.class)
public class ApplicantDocumentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ApplicantDocumentService applicantDocumentService;

	@Test
	public void uploadDocument_whenuploadDocument_thenReturnOk() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
		String name = "cedula";
		String email = "walter@yahoo.com";

		Mockito.doNothing().when(applicantDocumentService).uploadDocument(file, name, email, null);

		mvc.perform(fileUpload("/applicantDocument").file(file).accept(MediaType.APPLICATION_JSON).param("name", name)
				.param("email", email)).andExpect(status().isOk());
	}

}
