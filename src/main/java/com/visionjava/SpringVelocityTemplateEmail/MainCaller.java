package com.visionjava.SpringVelocityTemplateEmail;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.visionjava.SpringVelocityTemplateEmail.model.Student;


public class MainCaller {

  public static void main(String[] args) {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
	EmailSender emailSender = (EmailSender)context.getBean("emailSender");
	
	List<Student> studentList = new ArrayList<Student>();
	Date date = new GregorianCalendar().getTime();
	studentList.add( new Student("Amitabh", "Bachhan", 96.4563, date ) );
	studentList.add( new Student("John", "Smith", 89.456, date ) );
	studentList.add( new Student("Leonardo", "Decaprio", 65.34244, new GregorianCalendar().getTime() ) );
	studentList.add( new Student("Kate", "Winslet", 55.90744, new GregorianCalendar().getTime() ) );


Map<Integer,Student> studentMap = new HashMap<Integer,Student>();
studentMap.put(1, new Student("Amitabh", "Bachhan", 96.4563, date ));
studentMap.put(2, new Student("John", "Smith", 89.456, date ) );
studentMap.put(3,  new Student("Leonardo", "Decaprio", 65.34244, new GregorianCalendar().getTime() ));
	
	//emailSender.sendEmail("test@visionjava.com", "test@visionjava.com", "Velocity Template Students", studentList);

	emailSender.sendEmail("test@visionjava.com", "test@visionjava.com", "Velocity Template Students", studentMap);
  }

}
