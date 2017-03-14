package org.egreen.opensms.server.dao.impl;

import org.egreen.opensms.server.dao.CategoryDAOController;
import org.egreen.opensms.server.dao.ItemDAOController;
import org.egreen.opensms.server.entity.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */

@Repository
public class ItemDAOControllerImpl extends AbstractDAOController<Item,String> implements ItemDAOController {
    public ItemDAOControllerImpl() {
        super(Item.class, String.class);
    }


    @Override
    public List<Item> getAllCustomersByPagination(Integer limit, Integer offset) {

        Criteria criteria = getSession().createCriteria(entityType);
        if (limit!=null&&offset!=null) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
        }
        return criteria.list();
    }

    @Override
    public Integer removeCustomerById(String customerid) {
        Session session = getSession();
        String hql = "delete from Customer where customerid= :customerid";
        int i = session.createQuery(hql).setString("customerid", customerid).executeUpdate();
        return i;
    }




    @Override
    public List<Item> getAllCus() {
        Criteria criteria = getSession().createCriteria(entityType);
        criteria.addOrder(org.hibernate.criterion.Order.desc("sortingValue"));
        return criteria.list();
    }

    @Override
    public List<Item> getAll(String branchid,Integer limit, Integer offset) {
        Criteria criteria = getSession().createCriteria(entityType);
        criteria.add(Restrictions.eq("branchid", branchid));

        if (limit!=null&&offset!=null) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
        }
        return criteria.list();
    }





}
