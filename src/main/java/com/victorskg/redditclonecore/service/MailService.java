package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private final MailBuilder mailBuilder;

    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePrepare = mimeMessage -> {
            var helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("reddit@reddit.com");
            helper.setTo(notificationEmail.getRecipient());
            helper.setSubject(notificationEmail.getSubject());
            helper.setText(mailBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePrepare);
            log.info("Email de ativação enviado com sucesso!");
        } catch (MailException e) {
            throw new RedditException(
                    format("Ocorreu um erro ao enviar email de ativação para %s.", notificationEmail.getRecipient())
            );
        }

    }
}
