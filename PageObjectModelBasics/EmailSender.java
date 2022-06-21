package appmanager;

import cucumber.api.CucumberOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import tests.TestRunner;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

@PropertySource("classpath:*.properties")
public class EmailSender {

    public JavaMailSender emailSender = emailSender();
    FileSystemResource file = new FileSystemResource(new File("./src/test/resources/testing_image.png"));
//  static PropertyFileReader localreader  = new PropertyFileReader("local.properties");
    @Value("${sender.email}")
String email;
    @Value("${application.name}")
    String appname;


    public JavaMailSender emailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("email...............com"); // 
        mailSender.setPort(25);
        mailSender.setUsername(email);
        mailSender.setPassword("");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.debug", "true");
        return mailSender;
    }



    public void sendSimpleMessage(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            System.out.println("=============== Sent email successfully !!!! =================");
        }


        public void sendHTMLmessage(String to, String subject, String text){

            try {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper  helper = new MimeMessageHelper(message, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(text,true);
//                helper.addAttachment("Invoice", file);
                emailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        public String buildMessage(){

            String[] tags = TestRunner.class.getAnnotation(CucumberOptions.class).tags();
            StringBuilder executedScenarios = new StringBuilder();
            for(String name: tags){
                if(name.contains("@Smoke")){
                    executedScenarios.append(" \n Smoke ,");
                } if(name.contains("@Regression")){
                    executedScenarios.append("\n Regression ,");
                } if(name.contains("@Functional")){
                    executedScenarios.append("\n Functional.");
                } else{
                    executedScenarios.append("\n "+ name);
                }
            }
            StringBuilder tagName = new StringBuilder();
            for(String scenarios:tags){
                tagName.append(scenarios+" <br>");
            }
            BodyPart messageBodyPart = new MimeBodyPart();
            String header = "<h3   style = 'font-weight:normal;font-family:calibri,garamond,serif;'>Hi Team,</h3>";
            String body ="<div style = 'font-family:calibri,garamond,serif;font-size:16px;'>"+
                 " <p>"+"Started to execute "+appname +" automated test cases. </p>"+
                 " <p>"+"Test scenarios with the following tags will be executed: "+"</p>"+
                 " <p>"+executedScenarios.toString()+ "</p>"+"</div>";
                 String warningMessage = " <p><b>"+"THIS IS AN AUTOMATED MESSAGE - PLEASE DO NOT REPLY DIRECTLY TO THIS EMAIL!!!"+"</b></p>";
//                 "<img src='cid:resources/testing_image.png' height='100' width='800'/>";
            String footer = "<h3 style = 'font-weight:normal;font-family:calibri,garamond,serif;>Happy Testing! </h3>" +
                            "<h3 style = 'font-weight:normal;font-family:calibri,garamond,serif;> SQE team </h3>";
          return warningMessage+header+body+footer;
        }

    public String buildPostMessage(ArrayList<String> scrioList){

        int failTotal = 0;
        int passTotal = 0;
        StringBuilder scenarioName = new StringBuilder();
        String header = "<h3   style = 'font-weight:normal;font-family:calibri,garamond,serif;'>Hi Team,</h3>";
        for(String scenarios:scrioList){
            scenarioName.append("<li>"+scenarios+"</li>");
            if(scenarios.contains("FAIL")){
                failTotal++;
            }else{
                passTotal++;
            }
        }

        String body ="<div style = 'font-family:calibri,garamond,serif;font-size:16px;'>"+
                " <p>"+" Execution of "+appname +" automation test cases have completed." +
                " Following test scenarios have been executed. </p>" +

                scenarioName.toString();


                /*String link = "<br><li><a href='file://///"+getHostName()+"//"
                        +new File(ExtentCucumberFormatter.outputDirectory+"/report.html").getAbsolutePath()+"'>Click here to see the results</a></li>";*/

        String link = "<br><li><a href='file:///"
                +new File(ExtentCucumberFormatter.outputDirectory+"/report.html").getAbsolutePath()+"'>Click here to see the results</a></li>"+
                "<br> <H5>TOTAL FAILED TEST SCENARIOS - "+  failTotal+" <H5>"+
                "<H5>TOTAL PASSED TEST SCENARIOS - "+  passTotal+" <H5>";

        String footer = "<div style = 'font-family:calibri,garamond,serif;font-size:16px;' >" +
                        " <h3 style='font-weight:normal'>Happy Testing! </h3>" +
                        " <h3 style='font-weight:normal'> SQE team </h3> " ;
      String warningMessage=  " <p><b>"+"THIS IS AN AUTOMATED MESSAGE - PLEASE DO NOT REPLY DIRECTLY TO THIS EMAIL!!!"+"</b></p> </div>";
        return warningMessage+header+body+link+footer;///
    }

    public String getHostName(){

        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            return addr.getHostName();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
            return null;
        }
    }



}
