package com.tistory.kjharu.springbootmail.component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class MailComponent {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    public MailComponent(JavaMailSender mailSender, SpringTemplateEngine templateEngine)
        throws MessagingException {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        //생성 시 mimeMessage, mimeMessageHelper를 초기화해준다.
        mimeMessage = mailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    }


    /**
     * @param address 수신자의 이메일 주소
     * @param title   이메일의 제목
     * @param message 이메일 내용
     * @throws MessagingException
     */
    public void sendMail(String address, String title, String message) throws MessagingException {
        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(message, true);

        mailSender.send(mimeMessage);
    }


    /**
     * @param address      수신자의 이메일 주소
     * @param title        이메일의 제목
     * @param templatePath 이메일 내용으로 보낼 template의 경로
     * @param context      template의 변수를 채워줄 객체
     * @throws MessagingException
     */
    public void sendMail(String address, String title, String templatePath, Context context)
        throws MessagingException {

        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(templateEngine.process(templatePath, context), true);

        mailSender.send(mimeMessage);
    }


    /**
     * @param address  수신자의 이메일 주소
     * @param title    이메일 제목
     * @param message  이메일 내용
     * @param filePath 첨부할 파일의 경로
     * @param fileName 첨부될 파일이 표시될 이름
     * @throws MessagingException
     */
    public void sendMailWithFile(String address, String title, String message, String filePath,
        String fileName) throws MessagingException {

        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(message, true);

        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        mimeMessageHelper.addAttachment(fileName, fileSystemResource);

        mailSender.send(mimeMessage);
    }

}