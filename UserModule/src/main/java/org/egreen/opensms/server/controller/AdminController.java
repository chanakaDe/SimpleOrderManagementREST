package org.egreen.opensms.server.controller;

import org.apache.commons.io.IOUtils;
import org.egreen.opensms.server.entity.Admin;
import org.egreen.opensms.server.models.AdminModel;
import org.egreen.opensms.server.service.AdminDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
@RequestMapping("openmicro/v1/admin")
public class AdminController {

    @Autowired
    private AdminDAOService adminDAOService;


    /**
     * save admin
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save(@RequestBody Admin admin) {
        String res = adminDAOService.save(admin);
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
     * update admin
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage update(@RequestBody Admin admin) {
        String res = adminDAOService.update(admin);
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
    public List<Admin> getAll() {

        return adminDAOService.getAll();
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
        Admin admin = adminDAOService.login(branchId, username, password);
        if (admin != null) {
            AdminModel adminModel = new AdminModel();
            adminModel.setNic(admin.getNic());
            adminModel.setAdminid(admin.getAdminid());
            adminModel.setUsername(admin.getUsername());
            adminModel.setContactnum(admin.getContactnum());
            adminModel.setEmail(admin.getEmail());
            adminModel.setLogin(admin.isLogin());
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
    public Admin getAdminDetail(@RequestParam("userId") String userId) {
        return adminDAOService.getAdminDetail(userId);
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

        return adminDAOService.resetPassword(username, oldPassword, newPassword);
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
        return adminDAOService.logout(userId);
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Admin getob() {
        return new Admin();
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
