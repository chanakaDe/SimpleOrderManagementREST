package org.egreen.opensms.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Pramoda Fernando on 3/18/2015.
 */
@Entity
@Table(name = "admin")
@JsonIgnoreProperties
public class Admin implements EntityInterface <String> {

    private String   adminid;
    private String   nic;
    private String   username;
    private String   email;
    private String   contactnum;
    private String   password;
    private boolean  isLogin;
    private Integer  permission;


    @Id
    @Column(name = "adminid")
    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }





  

    @Override
    @Transient
    public String getId() {
        return getAdminid();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid='" + adminid + '\'' +
                ", nic='" + nic + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contactnum='" + contactnum + '\'' +
                ", password='" + password + '\'' +
                ", isLogin=" + isLogin +
                ", permission=" + permission +
                '}';
    }
}
