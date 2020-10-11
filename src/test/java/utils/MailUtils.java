package test.java.utils;

import io.restassured.http.ContentType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import test.java.utils.ReadProperty;
import static io.restassured.RestAssured.given;

public class MailUtils {
    public static void senEmail()throws IOException {
            ReadProperty readProperty= ReadProperty.getInstance();
            final String username = readProperty.getProperty("username");
            final String password = readProperty.getProperty("password");

            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("seleniumFrameworkTesting@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(readProperty.getProperty("emailCC")));
                message.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(readProperty.getProperty("emailTo")));
                message.setSubject("Cucumber Test Execution Report");
                message.setText("PFA cucmber report");

                MimeBodyPart messageBodyPart = new MimeBodyPart();

                Multipart multipart = new MimeMultipart();
                /*
                Update the report file path if required
                 */

                String file = "target/report.html";
                String fileName = "report.html";
                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);

                BodyPart bodyPart = new MimeBodyPart();
                String htmlText = readJson();
                bodyPart.setContent(htmlText, "text/html");
                multipart.addBodyPart(bodyPart);


                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);

                System.out.println("Sending Email");

                Transport.send(message);

                System.out.println("Email Sent");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
    }

    public static String readJson() throws IOException {
        /*
        Update the json path whenever required.
         */
        String json = new String(Files.readAllBytes(Paths.get("target/json/report.json")));
        return given().contentType(ContentType.JSON).
                body(json)
                .when().
                        post("http://selenium1234.pythonanywhere.com/getcucumbersummary").then().extract().body().asString();
    }
}