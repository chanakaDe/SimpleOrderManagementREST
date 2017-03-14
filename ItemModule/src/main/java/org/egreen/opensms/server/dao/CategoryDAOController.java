package org.egreen.opensms.server.dao;

import org.egreen.opensms.server.entity.Category;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
public interface CategoryDAOController extends DAOController<Category,String> {

    List<Category> getAllCustomersByPagination(Integer limit, Integer offset);

    Integer removeCustomerById(String branchId);



    List<Category> getAllCus();

    List<Category> getAll(String branchId,Integer limit, Integer offset);



}
