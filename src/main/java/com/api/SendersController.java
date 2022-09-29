package com.api;

import com.services.SenderService;
import com.shared.dto.ReportSender;
import com.shared.dto.Reports;
import com.shared.utils.Message;
import com.shared.utils.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping(path = "")
@ComponentScan({"com.service.SenderService"})
public class SendersController {

    @Autowired
    private SenderService senderService;

    @GetMapping("/check")
    public ResponseEntity<List<Sender>> getCheckSenders(@RequestParam("senders") String senderPath) throws FileNotFoundException {
        senderService.initPathSender(senderPath);
        List<Sender> senders = senderService.getSenders();
        List<Sender> checkSenders = senderService.checkSenders(senders);
        return new ResponseEntity<List<Sender>>(checkSenders, HttpStatus.OK);
    }

    @GetMapping("/send")
    public ResponseEntity<Reports> getBoard(@RequestParam("senders") String senderPath,
                                                 @RequestParam("receiver") String receiverPath,
                                                 @RequestParam("message") String message,
                                                 @RequestParam("subject") String subject) throws IOException {
        senderService.initPathSender(senderPath);
        senderService.initPathReceiver(receiverPath);
        Message msg = new Message(message);
        List<ReportSender> reportSenders = senderService.getReports(msg, subject);
        int totalSuccess = reportSenders.stream().mapToInt(report -> report.success).sum();
        int totalError = reportSenders.stream().mapToInt(report -> report.errors).sum();
        Reports reports = new Reports(reportSenders, totalSuccess, totalError);
        return new ResponseEntity<Reports>(reports, HttpStatus.OK);
    }
}
