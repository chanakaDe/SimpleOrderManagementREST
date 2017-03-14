package org.egreen.opensms.server.controller;

import org.egreen.opensms.server.entity.Item;
import org.egreen.opensms.server.models.ReturnIdModel;
import org.egreen.opensms.server.service.ItemDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("res/v1/item")
public class ItemController {

    @Autowired
    private ItemDAOService itemDAOService;


    /**
     * save Branch
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel save(@RequestBody Item item) {
        String res = itemDAOService.save(item);
        ReturnIdModel returnIdModel = new ReturnIdModel();
        returnIdModel.setId(res);
        return returnIdModel;

    }

    /**
     * Update Branch
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ReturnIdModel update(@RequestBody Item item) {
        String res = itemDAOService.update(item);
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
    public List<Item> getAll(@RequestHeader(value = "branchId") String branchId,
                             @RequestParam("limit") Integer limit,
                             @RequestParam("offset") Integer offset) {
        return itemDAOService.getAll(branchId, limit, offset);
    }

    /**
     * .
     * <p/>
     * getDetailsById
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "getDetailsById", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public Item getDetailsById(@RequestParam("itemId") String itemId) {
        return itemDAOService.getDetailsById(itemId);
    }


    /**
     * getFoodItemByCategoryId
     * 
     * 
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "getFoodItemByCategoryId", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<Item>  getFoodItemByCategoryId(@RequestParam("categoryId") String categoryId) {
        return itemDAOService.getFoodItemByCategoryId(categoryId);
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
        Integer res = itemDAOService.removeCustomerById(customerId);

        return res;

    }


    @RequestMapping(value = "getAllCus", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public List<Item> getAllCus() {
        return itemDAOService.getAllCus();
    }


    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    @CrossOrigin
    public Item getob() {
        return new Item();
    }


}
