package org.egreen.opensms.server.controller;

import org.apache.commons.io.IOUtils;
import org.egreen.opensms.server.entity.User;
import org.egreen.opensms.server.models.AdminModel;
import org.egreen.opensms.server.service.AdminDAOService;
import org.egreen.opensms.server.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("openmicro/v1/user")
public class UserController {

    @Autowired
    private UserDAOService userDAOService;


    /**
     * save user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save(@RequestBody User user) {
        String res = userDAOService.save(user);
        ResponseMessage responseMessage;
        if (res != null) {
            responseMessage = ResponseMessage.SUCCESS;
            responseMessage.setData(res);
        } else {
            responseMessage = ResponseMessage.DANGER;
            responseMessage.setData(res);
        }
        return responseMessage;
    }

    /**
     * update user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage update(@RequestBody User user) {
        String res = userDAOService.update(user);
        ResponseMessage responseMessage;
        if (res != null) {
            responseMessage = ResponseMessage.SUCCESS;
            responseMessage.setData(res);
        } else {
            responseMessage = ResponseMessage.DANGER;
            responseMessage.setData(res);
        }
        return responseMessage;
    }

    /**
     * getAll
     *
     * @return
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<User> getAll() {

        return userDAOService.getAll();
    }


    /**
     * login
     *
     * @param username
     * @param password
     * @return
     * @RequestHeader BranchId
     * @RequestHeader UserId
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AdminModel login(@RequestHeader(value = "branchId") String branchId,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password) {
        User user = userDAOService.login(branchId, username, password);
        if (user != null) {
            AdminModel adminModel = new AdminModel();
            adminModel.setNic(user.getNic());
            adminModel.setAdminid(user.getUserid());
            adminModel.setUsername(user.getUsername());
            adminModel.setContactnum(user.getContactnum());
            adminModel.setEmail(user.getEmail());
            adminModel.setLogin(user.isLogin());
            return adminModel;
        } else {
            return new AdminModel();
        }
    }

    /**
     * getAdminDetail
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getAdminDetail", method = RequestMethod.GET)
    @ResponseBody
    public User getAdminDetail(@RequestParam("userId") String userId) {
        return userDAOService.getAdminDetail(userId);
    }

    /**
     * Logout
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(@RequestParam("userName") String username,
                                @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword) {

        return userDAOService.resetPassword(username, oldPassword, newPassword);
    }

    /**
     * Logout
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout(@RequestParam("userId") String userId) {
        return userDAOService.logout(userId);
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public User getob() {
        return new User();
    }

    /**
     * getString
     *
     * @return
     */
    @RequestMapping(value = "getString", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getString() {
        return "Open Micro";
    }

    /**
     *
     * getDateTime
     *
     * @return
     */
    @RequestMapping(value = "getDateTime", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Long getDateTime() {
        return new Date().getTime();

    }

    /**
     * getFile
     *
     * @param response
     * @param date
     * @param username
     */
    @RequestMapping(value = "getFile", method = RequestMethod.GET, headers = "Accept=application/json")
    public void getFile(HttpServletResponse response,
                        @RequestParam("date") String date,
                        @RequestParam("username") String username) {
        String path = System.getProperty("user.home") + "/systemlog/";
        File file = new File("" + path + "" + date + "/" + username + ".txt");
        try {
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("application/pdf");

            response.setHeader("Content-Disposition", "attachment; filename=" + date + "-" + username + ".csv");

            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
