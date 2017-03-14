package org.egreen.opensms.server.dao;

import org.egreen.opensms.server.entity.TableOrderHasItem;
import org.egreen.opensms.server.entity.TableOrderHasItem;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */
public interface TableOrderHasItemDAOController extends DAOController<TableOrderHasItem,String> {

    List<TableOrderHasItem> getAllCustomersByPagination(Integer limit, Integer offset);

    Integer removeCustomerById(String branchId);



    List<TableOrderHasItem> getAllCus();

    List<TableOrderHasItem> getAll(String branchId, Integer limit, Integer offset);



}
