package org.egreen.opensms.server.service;

import org.egreen.opensms.server.dao.UserDAOController;
import org.egreen.opensms.server.entity.User;
import org.egreen.opensms.server.utils.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
@Service
public class UserDAOService {

    @Autowired
    private UserDAOController userDAOController;


    @Autowired
    private AuthenticationController authenticationController;


    private List<User> all;


    /**
     * Customer SignUp
     *
     * @param user
     * @return
     */
    public String save(User user) {
        String id = new Date().getTime() + "";
        Hashids hashids = new Hashids(id);
        String hexaid = hashids.encodeHex(String.format("%040x", new BigInteger(1, id.getBytes())));
        String newid = hexaid + "" + randomString(10);
        user.setUserid(newid);

        String userPassword = authenticationController.getEncryptWord(user.getPassword());
        user.setPassword(userPassword);

        String aLong = userDAOController.create(user);
        return aLong;
    }

    /**
     * Get Random String
     *
     * @param len
     * @return
     * @author Pramoda Nadeeshan Fernando
     * @version 1.0
     * @since 2015-02-12 4.26PM
     */
    private String randomString(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


    public User login(String BranchId, String username, String password) {
        String userPassword = authenticationController.getEncryptWord(password);
        User admin = userDAOController.login(username, userPassword);
        return admin;
    }

    public List<User> getAll() {
        return userDAOController.getAll();
    }

    public String update(User admin) {
        return userDAOController.update(admin);
    }

    public String logout(String userId) {
        User read = userDAOController.read(userId);
        if (read != null) {
            read.setLogin(false);
            return userDAOController.update(read);
        }
        return null;
    }

    public User getAdminDetail(String userId) {
        return userDAOController.read(userId);
    }

    public String resetPassword(String username, String oldPassword, String newPassword) {

        String userPassword = authenticationController.getEncryptWord(oldPassword);
        User admin = userDAOController.checkAccount(username, userPassword);
        if (admin != null) {
            String newpw = authenticationController.getEncryptWord(newPassword);

            admin.setPassword(newpw);
            String update = userDAOController.update(admin);
            return update;
        } else {
            return null;
        }
    }
}
