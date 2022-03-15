package report;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendMailWithAttachment {
    public static void mailTrigger()
    {
        String toAddress="balaji@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("balaji.gopi@honeywell.com", "Nila@12345");
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("balaji.gopi@honeywell.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
            message.setSubject("TemaLine EBI Application - Automation Test Execution Report");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Dear Team,"+'\n'+""+'\n'+"Our test suite has just finished its execution. "+'\n'+"Failure case's will be analyzed by us and will post out the observation. Please find the attachment for the test run report."+'\n'+""+'\n'+"This email was sent automatically by HBS TemaLine. Please do not reply."+'\n'+""+'\n'+"Thanks,"+'\n'+"Balaji.");

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String workingDir=System.getProperty("user.dir");
            String filename = workingDir+"\\ExtentReports\\TestReport_"+ExtentManager.datetimestamp+".html";

            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName("TemaLineExecution"+""+" Test"+""+" Report.html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("=====Email Sent=====");
        }
        catch (MessagingException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}