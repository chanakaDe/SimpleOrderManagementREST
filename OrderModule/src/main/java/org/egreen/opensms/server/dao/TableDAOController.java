package org.egreen.opensms.server.dao;

import org.egreen.opensms.server.entity.Table;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
public interface TableDAOController extends DAOController<Table,String> {

    List<Table> getAllCustomersByPagination(Integer limit, Integer offset);

    Integer removeCustomerById(String branchId);



    List<Table> getAllCus();

    List<Table> getAll(String branchId,Integer limit, Integer offset);



}
