package org.egreen.opensms.server.service;

import org.egreen.opensms.server.dao.TableDAOController;
import org.egreen.opensms.server.dao.TableOrderHasItemDAOController;
import org.egreen.opensms.server.entity.TableOrderHasItem;
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
public class TableOrderHasItemDAOService {

    @Autowired
    private TableOrderHasItemDAOController customerDAOController;


    private List<TableOrderHasItem> all;
    private String id;
    private List<TableOrderHasItem> allCus;
    private Long nextId;
    private List<TableOrderHasItem> customerCount;


    /**
     * customer SignUp
     *
     * @param customer
     * @return
     */
    public String save(TableOrderHasItem customer) {
        String id = new Date().getTime() + "";
        Hashids hashids = new Hashids(id);
        String hexaid = hashids.encodeHex(String.format("%040x", new BigInteger(1, id.getBytes())));
        String newid = hexaid + "" + randomString(10);
        customer.setTableId(newid);


        String s = customerDAOController.create(customer);
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
    public List<TableOrderHasItem> getAll(String branchId, Integer limit, Integer offset) {

        return customerDAOController.getAll(branchId, limit, offset);
    }
//
    public List<TableOrderHasItem> getAll() {

        return customerDAOController.getAll();
    }

//
    public String update(TableOrderHasItem customer) {
        return customerDAOController.update(customer);
    }

    public Integer removeCustomerById(String customerId) {
        return customerDAOController.removeCustomerById(customerId);
    }

//
    public TableOrderHasItem getDetailsById(String customerId) {
        return customerDAOController.read(customerId);
    }
//

//
    public List<TableOrderHasItem> getAllCus() {
        return customerDAOController.getAllCus();
    }

}
