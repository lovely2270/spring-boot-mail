package com.tistory.kjharu.springbootmail.controller;

import com.tistory.kjharu.springbootmail.component.MailComponent;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;


//address와 파일경로는 직접 작성해주세요.
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailComponent mailComponent;


    /**
     * 심플한 메시지 전송
     *
     * @return 첫화면
     * @throws MessagingException
     */
    @GetMapping("/sendSimpleMail")
    public String sendSimpleMail() throws MessagingException {
        String address = "[수신할 메일 주소]";
        String title = "심플 메일 테스트";
        String msg = "이메일 내용입니다.";
        mailComponent.sendMail(address, title, msg);
        return "/index";
    }


    /**
     * template engine을 이용한 메일 전송
     *
     * @return 첫화면
     * @throws MessagingException
     */
    @GetMapping("/sendHTMLMail")
    public String sendHTMLMail() throws MessagingException {
        String address = "[수신할 메일 주소]";
        String title = "html template 메일 테스트";
        String templatePath = "/mail/mail-template-example.html";
        Context context = new Context();
        context.setVariable("name", "kjharu");
        context.setVariable("blog", "kjharu-record.tistory.com");
        context.setVariable("body", "메일 전송");

        mailComponent.sendMail(address, title, templatePath, context);

        return "/index";
    }


    /**
     * 파일첨부 메시지 전송
     *
     * @return 첫화면
     * @throws MessagingException
     */
    @GetMapping("/sendFileMail")
    public String sendFileMail() throws MessagingException {
        String address = "[수신할 메일 주소]";
        String title = "파일 첨부 메일 테스트";
        String msg = "이메일 내용입니다.";
        String filePath = "[파일경로]/메일템플릿.png";
        String fileName = "첨부파일이름.png";

        mailComponent.sendMailWithFile(address, title, msg, filePath, fileName);

        return "/index";
    }

}
