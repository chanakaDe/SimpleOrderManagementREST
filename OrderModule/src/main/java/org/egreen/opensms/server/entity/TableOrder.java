package org.egreen.opensms.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Pramoda Fernando on 3/18/2015.
 */
@Entity
@javax.persistence.Table(name = "tableorder")
@JsonIgnoreProperties
public class TableOrder implements EntityInterface <String> {

    private String tableOrderId;
    private String tableId;
    private String waiterId;
    private Timestamp datetime;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private BigDecimal balanceAmount;


    @Id
    @Column(name = "tableOrderId")
    public String getTableOrderId() {
        return tableOrderId;
    }

    public void setTableOrderId(String tableOrderId) {
        this.tableOrderId = tableOrderId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }





    @Override
    @Transient
    public String getId() {
        return getTableOrderId();
    }

    @Override
    public String toString() {
        return "TableOrder{" +
                "balanceAmount=" + balanceAmount +
                ", tableOrderId='" + tableOrderId + '\'' +
                ", tableId='" + tableId + '\'' +
                ", waiterId='" + waiterId + '\'' +
                ", datetime=" + datetime +
                ", totalAmount=" + totalAmount +
                ", payAmount=" + payAmount +
                '}';
    }
}
