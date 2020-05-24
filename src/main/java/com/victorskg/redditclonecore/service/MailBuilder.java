package com.victorskg.redditclonecore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailBuilder {

    private final TemplateEngine templateEngine;

    String build(String message) {
        var context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mail", context);
    }

}
