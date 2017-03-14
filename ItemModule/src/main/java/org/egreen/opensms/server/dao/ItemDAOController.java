package org.egreen.opensms.server.dao;

import org.egreen.opensms.server.entity.Item;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
public interface ItemDAOController extends DAOController<Item,String> {

    List<Item> getAllCustomersByPagination(Integer limit, Integer offset);

    Integer removeCustomerById(String branchId);



    List<Item> getAllCus();

    List<Item> getAll(String branchId, Integer limit, Integer offset);



}
