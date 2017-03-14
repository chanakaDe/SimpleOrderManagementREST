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
@javax.persistence.Table(name = "tableorderhasfood")
@JsonIgnoreProperties
public class TableOrderHasItem implements EntityInterface <String> {

    private String tableOrderId;
    private String tableId;
    private String waiterId;
    private String foodId;
    private Timestamp datetime;
    private String type;
    private BigDecimal qty;
    private BigDecimal amount;



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

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    @Override
    @Transient
    public String getId() {
        return getTableOrderId();
    }

    @Override
    public String toString() {
        return "TableOrderHasFood{" +
                "tableOrderId='" + tableOrderId + '\'' +
                ", tableId='" + tableId + '\'' +
                ", waiterId='" + waiterId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", datetime=" + datetime +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", amount=" + amount +
                '}';
    }
}
