package org.egreen.opensms.server.controller;

import org.egreen.opensms.server.entity.TableOrderHasItem;
import org.egreen.opensms.server.models.ReturnIdModel;
import org.egreen.opensms.server.service.TableDAOService;
import org.egreen.opensms.server.service.TableOrderHasItemDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("res/v1/tableOrderHasItem")
public class TableOrderHasItemController {

    @Autowired
    private TableOrderHasItemDAOService tableOrderHasItemDAOService;


    /**
     * save Branch
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel save(@RequestBody TableOrderHasItem customer) {
        String res = tableOrderHasItemDAOService.save(customer);
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
    public ReturnIdModel update(@RequestBody TableOrderHasItem customer) {
        String res = tableOrderHasItemDAOService.update(customer);
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
    public List<TableOrderHasItem> getAll(@RequestHeader(value = "branchId") String branchId,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("offset") Integer offset) {
        return tableOrderHasItemDAOService.getAll(branchId, limit, offset);
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
    public TableOrderHasItem getDetailsById(@RequestParam("customerId") String customerId) {
        return tableOrderHasItemDAOService.getDetailsById(customerId);
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
        Integer res = tableOrderHasItemDAOService.removeCustomerById(customerId);

        return res;

    }


    @RequestMapping(value = "getAllCus", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<TableOrderHasItem> getAllCus() {
        return tableOrderHasItemDAOService.getAllCus();
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public TableOrderHasItem getob() {
        return new TableOrderHasItem();
    }


}
