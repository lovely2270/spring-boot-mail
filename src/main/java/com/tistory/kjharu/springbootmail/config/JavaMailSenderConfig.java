package com.tistory.kjharu.springbootmail.config;

import com.google.gson.Gson;
import com.tistory.kjharu.springbootmail.service.ConfigService;
import java.util.Map;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailSenderConfig {

    private final ConfigService configService;

    public JavaMailSenderConfig(ConfigService configService) {
        this.configService = configService;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        String mailConfig = configService.getConfig("MAIL_CONFIG").getValue();
        Map emailConfigMap = new Gson().fromJson(mailConfig, Map.class);

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfigMap.get("host").toString());
        javaMailSender.setPort((int) (Double.parseDouble(emailConfigMap.get("port").toString())));
        javaMailSender.setUsername(emailConfigMap.get("user").toString());
        javaMailSender.setPassword(emailConfigMap.get("password").toString());

        Properties properties = new Properties();
        properties
            .setProperty("mail.smtp.starttls.enable", emailConfigMap.get("use_tls").toString());

        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}
