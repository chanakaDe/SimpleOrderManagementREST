package org.egreen.opensms.server.controller;

import org.egreen.opensms.server.entity.Table;
import org.egreen.opensms.server.models.ReturnIdModel;
import org.egreen.opensms.server.service.TableDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("res/v1/table")
public class TableController {

    @Autowired
    private TableDAOService tableDAOService;


    /**
     * save Branch
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel save(@RequestBody Table customer) {
        String res = tableDAOService.save(customer);
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
    public ReturnIdModel update(@RequestBody Table customer) {
        String res = tableDAOService.update(customer);
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
    public List<Table> getAll(@RequestHeader(value = "branchId") String branchId,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("offset") Integer offset) {
        return tableDAOService.getAll(branchId, limit, offset);
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
    public Table getDetailsById(@RequestParam("customerId") String customerId) {
        return tableDAOService.getDetailsById(customerId);
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
        Integer res = tableDAOService.removeCustomerById(customerId);

        return res;

    }


    @RequestMapping(value = "getAllTables", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<Table> getAllTables() {
        return tableDAOService.getAllTables();
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public Table getob() {
        return new Table();
    }


}
