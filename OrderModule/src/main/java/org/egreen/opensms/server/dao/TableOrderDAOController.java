package org.egreen.opensms.server.dao;

import org.egreen.opensms.server.entity.TableOrder;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
public interface TableOrderDAOController extends DAOController<TableOrder,String> {

    List<TableOrder> getAllCustomersByPagination(Integer limit, Integer offset);

    Integer removeCustomerById(String branchId);



    List<TableOrder> getAllCus();

    List<TableOrder> getAll(String branchId, Integer limit, Integer offset);



}
