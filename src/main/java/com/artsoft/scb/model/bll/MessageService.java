package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.artsoft.scb.controller.configuration.AWSEmailCredentialProvider;
import com.artsoft.scb.model.bll.interfaces.IMessageService;
import com.artsoft.scb.model.entity.AWScredential;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.*;

@Service
public class MessageService extends ExceptionService implements IMessageService {

	@Autowired
	private HelperService helperService;

	@Autowired
	private AWSEmailCredentialProvider awsEmailCredentialProvider ;
	
	// Replace sender@example.com with your "From" address.
	// This address must be verified with Amazon SES.
	static final String FROM = "user@goidea.com.co";

	// Replace recipient@example.com with a "To" address. If your account
	// is still in the sandbox, this address must be verified.
	// static final String TO = "dknieto.06@gmail.com";

	// The configuration set to use for this email. If you do not want to use a
	// configuration set, comment the next line and line 60.
	// static final String CONFIGSET = "ConfigSet";

	// The subject line for the email.
	// static final String SUBJECT = "Ich liebe dich";

	// The HTML body for the email.
	// static final String HTMLBODY = "<h1>Ich liebe dich</h1>"
	// + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
	// + "Amazon SES</a> using the <a
	// href='https://aws.amazon.com/sdk-for-java/'>" + "AWS SDK for Java</a>";

	// The email body for recipients with non-HTML email clients.
	// static final String TEXTBODY = "This email was sent through Amazon SES "
	// + "using the AWS SDK for Java.";

	// The subject line for the email.

	@Override
	public boolean sendMessage(String htmlBody, List<String> destinies, String subject) throws Exception {
		try {
			String textBody = helperService.RemoveHtmlTags(htmlBody);
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(awsEmailCredentialProvider)
					// Replace US_WEST_2 with the AWS Region you're using for
					// Amazon SES.
					.withRegion(Regions.US_EAST_1).build();
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(
							new Destination().withToAddresses(destinies))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBody))
									.withText(new Content().withCharset("UTF-8").withData(textBody)))
							.withSubject(new Content().withCharset("UTF-8").withData(subject)))
					.withSource(FROM);
			// Comment or remove the next line if you are not using a
			// configuration set
			// .withConfigurationSetName(CONFIGSET);
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
			throwException("Response", "The email was not sent. Error message: " + ex.getMessage());
		}
		return true;
	}

}
