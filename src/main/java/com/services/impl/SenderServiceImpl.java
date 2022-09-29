package com.services.impl;

import com.api.SenderEmail;
import com.config.Constant;
import com.services.SenderService;
import com.shared.dto.ReportSender;
import com.shared.utils.EmailHandler;
import com.shared.utils.Message;
import com.shared.utils.Sender;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class SenderServiceImpl implements SenderService, Serializable {
    private String pathSender;
    private String pathReceiver;

    public void initPathSender(String fileSender) {
        this.pathSender = Constant.ABSOLUTE_PATH + fileSender + Constant.EXTENSION_TXT;
    }

    public void initPathReceiver(String pathReceiver) {
        this.pathReceiver = Constant.ABSOLUTE_PATH + pathReceiver + Constant.EXTENSION_TXT;
    }

    public List<Sender> getSenders() throws FileNotFoundException {
        FileReader fileReader = new FileReader(pathSender);
        Scanner scanner = new Scanner(fileReader);
        EmailHandler emailHandler = new EmailHandler();
        List<com.shared.utils.Sender> senders = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String logPass = scanner.nextLine();
            String[] loginPass = logPass.split(":");

            senders.add(new Sender(emailHandler.getEmail(loginPass[0]), loginPass[1]));
        }
        return senders;
    }

    public List<Sender> checkSenders(List<Sender> senders) {
        List<Sender> result = new ArrayList<>();
        senders.stream().forEach(sender -> {
            try {
                SenderEmail senderEmail = new SenderEmail(sender.getLogin(), sender.getPassword());
                senderEmail.check(sender.getLogin());
                result.add(new Sender(sender.getLogin(), sender.getPassword(), true));
            }catch (Exception e) {

            }
        });
        return result;
    }

    public List<ReportSender> getReports(Message msg, String subject) throws FileNotFoundException {
        FileReader fileReader = new FileReader(pathReceiver);
        Scanner scanner = new Scanner(fileReader);
        List<ReportSender> logger = new ArrayList<>();

        checkSenders(getSenders())
                .stream().filter(Sender::isLogin)
            .forEach(send -> {
            SenderEmail emailSender = new SenderEmail(send.getLogin(), send.getPassword());
            int success = 0;
            int errors = 0;
            List<String> log = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine();
                System.out.println(email);
                String status;
                try {
                    status = emailSender.send(email, msg.getMessage(), subject);
                    log.add(String.format("%s - %s", email, status));
                    success++;
                }catch (Exception e) {
                    log.add(String.format("%s - ошибка", email));
                    errors++;
                }
            }
            log.add(String.format("%d - отправлено, %d - ошибок", success, errors));
            logger.add(new ReportSender(send.getLogin(), log, success, errors));
        });
        return logger;
    }
}
