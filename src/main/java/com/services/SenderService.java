package com.services;

import com.shared.dto.ReportSender;
import com.shared.utils.Message;
import com.shared.utils.Sender;

import java.io.FileNotFoundException;
import java.util.List;

public interface SenderService {
    List<Sender> getSenders() throws FileNotFoundException;
    List<Sender> checkSenders(List<Sender> senders);
    void initPathSender(String fileSender);
    void initPathReceiver(String pathReceiver);
    List<ReportSender> getReports(Message msg, String subject) throws FileNotFoundException;
}
