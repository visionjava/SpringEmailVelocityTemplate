package com.visionjava.SpringVelocityTemplateEmail;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.visionjava.SpringVelocityTemplateEmail.model.Student;

@Component("emailSender")
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public void sendEmail(final String to, final String from, final String subject, final Object studentList) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {

				StringWriter mergedContent = new StringWriter();
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("students", studentList);
				velocityContext.put("date", new DateTool());
				velocityContext.put("number", new NumberTool());
				velocityEngine.mergeTemplate("emailTemplate.vm", "UTF-8", velocityContext, mergedContent);
				
				//Sending Message
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,	true);
				message.setTo(to);
				message.setFrom(from);
				message.setSubject(subject);
				message.setText(mergedContent.toString(), true);
				message.addAttachment("Studentdetails.html", new ByteArrayResource(mergedContent.toString().getBytes(), "Confirmation"));
			}
		};
		this.mailSender.send(preparator);
	}
}
