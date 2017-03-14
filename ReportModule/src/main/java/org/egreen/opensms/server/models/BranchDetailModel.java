package org.egreen.opensms.server.models;

import java.math.BigDecimal;

/**
 * Created by pramoda-nf on 3/8/16.
 */
public class BranchDetailModel {

    private String branchCode;
    private Long totalMemCount;
    private Long activeMemCount;
    private Long deActiveMemCount;
    private BigDecimal pendingLoanAmount;


    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Long getTotalMemCount() {
        return totalMemCount;
    }

    public void setTotalMemCount(Long totalMemCount) {
        this.totalMemCount = totalMemCount;
    }

    public Long getActiveMemCount() {
        return activeMemCount;
    }

    public void setActiveMemCount(Long activeMemCount) {
        this.activeMemCount = activeMemCount;
    }

    public Long getDeActiveMemCount() {
        return deActiveMemCount;
    }

    public void setDeActiveMemCount(Long deActiveMemCount) {
        this.deActiveMemCount = deActiveMemCount;
    }

    public BigDecimal getPendingLoanAmount() {
        return pendingLoanAmount;
    }

    public void setPendingLoanAmount(BigDecimal pendingLoanAmount) {
        this.pendingLoanAmount = pendingLoanAmount;
    }
}
