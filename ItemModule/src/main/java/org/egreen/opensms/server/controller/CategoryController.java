package org.egreen.opensms.server.controller;

import org.egreen.opensms.server.entity.Category;
import org.egreen.opensms.server.models.ReturnIdModel;
import org.egreen.opensms.server.service.CategoryDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("res/v1/customer")
public class CategoryController {

    @Autowired
    private CategoryDAOService customerDAOService;


    /**
     * save Branch
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel save(@RequestBody Category customer) {
        String res = customerDAOService.save(customer);
        ReturnIdModel returnIdModel = new ReturnIdModel();
        returnIdModel.setId(res);
        return returnIdModel;

    }

    /**
     * Update Branch
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel update(@RequestBody Category customer) {
        String res = customerDAOService.update(customer);
        ReturnIdModel returnIdModel = new ReturnIdModel();
        returnIdModel.setId(res);
        return returnIdModel;

    }


    /**
     * Get All
     *
     * @return
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<Category> getAll() {
        return customerDAOService.getAll();
    }

    /**
     * .
     * <p/>
     * getDetailsById
     *
     * @param customerId
     * @return
     */
    @RequestMapping(value = "getDetailsById", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public Category getDetailsById(@RequestParam("customerId") String customerId) {
        return customerDAOService.getDetailsById(customerId);
    }


    /**
     * removeBranchById
     *
     * @param customerId
     * @return
     */
    @RequestMapping(value = "removeCustomerById", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer removeCustomerById(@RequestParam("customerId") String customerId) {
        Integer res = customerDAOService.removeCustomerById(customerId);

        return res;

    }




    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public Category getob() {
        return new Category();
    }


}
