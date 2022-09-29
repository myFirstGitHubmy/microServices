package com.shared.dto;

import java.util.List;

public class Reports {
    public List<ReportSender> reports;
    public int totalSuccess;
    public int totalError;

    public Reports(List<ReportSender> reports, int totalSuccess, int totalError) {
        this.reports = reports;
        this.totalSuccess = totalSuccess;
        this.totalError = totalError;
    }
}
