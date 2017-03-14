package org.egreen.opensms.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Pramoda Fernando on 3/18/2015.
 */
@Entity
@javax.persistence.Table(name = "restable")
@JsonIgnoreProperties
public class Table implements EntityInterface <String> {

    private String tableRowId;
    private String name;
    private String hallName;


    @Id
    @Column(name = "tableRowId")
    public String getTableRowId() {
        return tableRowId;
    }

    public void setTableRowId(String tableRowId) {
        this.tableRowId = tableRowId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }






    @Override
    @Transient
    public String getId() {
        return getTableRowId();
    }


    @Override
    public String toString() {
        return "Table{" +
                "tableRowId='" + tableRowId + '\'' +
                ", name='" + name + '\'' +
                ", hallName='" + hallName + '\'' +
                '}';
    }
}
