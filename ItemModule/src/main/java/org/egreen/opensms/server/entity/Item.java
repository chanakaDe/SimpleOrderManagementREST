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
@javax.persistence.Table(name = "item")
@JsonIgnoreProperties
public class Item implements EntityInterface <String> {

    private String itemId;
    private String categoryId;
    private String name;
    private String personCount;
    private BigDecimal largePrice;
    private BigDecimal regularPrice;
    private BigDecimal smallPrice;
    private Timestamp datetime;


    @Id
    @Column(name = "itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public BigDecimal getLargePrice() {
        return largePrice;
    }

    public void setLargePrice(BigDecimal largePrice) {
        this.largePrice = largePrice;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(BigDecimal smallPrice) {
        this.smallPrice = smallPrice;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }







    @Override
    @Transient
    public String getId() {
        return getItemId();
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", personCount='" + personCount + '\'' +
                ", largePrice=" + largePrice +
                ", regularPrice=" + regularPrice +
                ", smallPrice=" + smallPrice +
                ", datetime=" + datetime +
                '}';
    }
}
