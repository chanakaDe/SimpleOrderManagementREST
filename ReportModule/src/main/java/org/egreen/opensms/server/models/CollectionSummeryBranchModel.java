package org.egreen.opensms.server.models;

import java.math.BigDecimal;

/**
 * Created by pramoda-nf on 3/1/16.
 */
public class CollectionSummeryBranchModel {


    private String centerCode;
    private String centerName;
    private BigDecimal collectionAmount;


    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public BigDecimal getCollectionAmount() {
        return collectionAmount;
    }

    public void setCollectionAmount(BigDecimal collectionAmount) {
        this.collectionAmount = collectionAmount;
    }
}
