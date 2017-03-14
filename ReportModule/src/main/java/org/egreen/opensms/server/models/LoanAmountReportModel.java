package org.egreen.opensms.server.models;

import java.math.BigDecimal;

/**
 * Created by pramoda-nf on 2/18/16.
 */
public class LoanAmountReportModel {

    private BigDecimal totalLoanAmount;
    private Integer loanCount;
    private BigDecimal totalCollection;
    private BigDecimal totalUnderpayment;
    private BigDecimal totalNotPaid ;

    public BigDecimal getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(BigDecimal totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Integer getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Integer loanCount) {
        this.loanCount = loanCount;
    }

    public BigDecimal getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(BigDecimal totalCollection) {
        this.totalCollection = totalCollection;
    }

    public BigDecimal getTotalUnderpayment() {
        return totalUnderpayment;
    }

    public void setTotalUnderpayment(BigDecimal totalUnderpayment) {
        this.totalUnderpayment = totalUnderpayment;
    }

    public BigDecimal getTotalNotPaid() {
        return totalNotPaid;
    }

    public void setTotalNotPaid(BigDecimal totalNotPaid) {
        this.totalNotPaid = totalNotPaid;
    }
}
