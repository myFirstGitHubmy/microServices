package com.shared.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportSender {
    public String sender;
    public List<String> receivers;
    public int success;
    public int errors;

    public ReportSender(String sender, List<String> receivers, int success, int errors) {
        this.sender = sender;
        this.receivers = receivers;
        this.success = success;
        this.errors = errors;
    }

    public String toString() {
        return String.format(
                "Sender - %s\n" +
                receivers.stream().collect(Collectors.joining("\n")) +
                "success - %d; " +
                "errors - %d", this.sender, this.success, this.errors
        );
    }
}
