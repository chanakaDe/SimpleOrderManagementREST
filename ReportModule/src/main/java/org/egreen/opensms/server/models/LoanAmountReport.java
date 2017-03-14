package org.egreen.opensms.server.models;

import java.sql.Timestamp;

/**
 * Created by pramoda-nf on 2/18/16.
 */
public class LoanAmountReport {

     private  String id;
     private  Timestamp firstDate;
     private  Timestamp secondDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }

    public Timestamp getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Timestamp secondDate) {
        this.secondDate = secondDate;
    }
}
