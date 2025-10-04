package io.app.service.impl;

import io.app.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Override
    public boolean sendMail(String to, String subject, String content) {
        return false;
    }
}
