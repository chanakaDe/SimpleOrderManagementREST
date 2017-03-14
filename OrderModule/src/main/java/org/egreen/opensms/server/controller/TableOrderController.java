package org.egreen.opensms.server.controller;

import org.egreen.opensms.server.entity.TableOrder;
import org.egreen.opensms.server.models.ReturnIdModel;
import org.egreen.opensms.server.service.TableDAOService;
import org.egreen.opensms.server.service.TableOrderDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("res/v1/tableOrder")
public class TableOrderController {

    @Autowired
    private TableOrderDAOService tableOrderDAOService;


    /**
     * save Branch
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel save(@RequestBody TableOrder customer) {
        String res = tableOrderDAOService.save(customer);
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
    public ReturnIdModel update(@RequestBody TableOrder customer) {
        String res = tableOrderDAOService.update(customer);
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
    public List<TableOrder> getAll(@RequestHeader(value = "branchId") String branchId,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("offset") Integer offset) {
        return tableOrderDAOService.getAll(branchId, limit, offset);
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
    public TableOrder getDetailsById(@RequestParam("customerId") String customerId) {
        return tableOrderDAOService.getDetailsById(customerId);
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
        Integer res = tableOrderDAOService.removeCustomerById(customerId);

        return res;

    }


    @RequestMapping(value = "getAllCus", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<TableOrder> getAllCus() {
        return tableOrderDAOService.getAllCus();
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public TableOrder getob() {
        return new TableOrder();
    }


}
