package org.egreen.opensms.server.models;

import org.egreen.opensms.server.entity.Category;

/**
 * Created by pramoda-nf on 9/10/15.
 */
public class CustomerModel  extends Category {

    private String businessTypeName;


    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }
}
