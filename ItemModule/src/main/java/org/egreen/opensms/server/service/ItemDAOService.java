package org.egreen.opensms.server.service;

import org.egreen.opensms.server.dao.CategoryDAOController;
import org.egreen.opensms.server.dao.ItemDAOController;
import org.egreen.opensms.server.entity.Item;
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
public class ItemDAOService {

    @Autowired
    private ItemDAOController itemDAOController;


    private List<Item> all;
    private String id;
    private List<Item> allCus;
    private Long nextId;
    private List<Item> customerCount;


    /**
     * item SignUp
     *
     * @param item
     * @return
     */
    public String save(Item item) {
        String id = new Date().getTime() + "";
        Hashids hashids = new Hashids(id);
        String hexaid = hashids.encodeHex(String.format("%040x", new BigInteger(1, id.getBytes())));
        String newid = hexaid + "" + randomString(10);
        item.setItemId(newid);


        String s = itemDAOController.create(item);
        return s;
    }
//
//
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
//
//
    public List<Item> getAll(String branchId, Integer limit, Integer offset) {

        return itemDAOController.getAll(branchId, limit, offset);
    }
//
    public List<Item> getAll() {

        return itemDAOController.getAll();
    }

//
    public String update(Item item) {
        return itemDAOController.update(item);
    }

    public Integer removeCustomerById(String customerId) {
        return itemDAOController.removeCustomerById(customerId);
    }

//
    public Item getDetailsById(String itemId) {
        return itemDAOController.read(itemId);
    }
//

//
    public List<Item> getAllCus() {
        return itemDAOController.getAllCus();
    }


    public List<Item> getFoodItemByCategoryId(String categoryId) {
        return itemDAOController.getAllByPropertyByStringValue(categoryId,"categoryId");
    }
}
