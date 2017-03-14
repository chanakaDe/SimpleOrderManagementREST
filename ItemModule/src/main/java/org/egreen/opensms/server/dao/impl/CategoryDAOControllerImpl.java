package org.egreen.opensms.server.dao.impl;

import org.egreen.opensms.server.dao.CategoryDAOController;
import org.egreen.opensms.server.entity.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pramoda Fernando on 1/14/2015.
 */

@Repository
public class CategoryDAOControllerImpl extends AbstractDAOController<Category,String> implements CategoryDAOController {
    public CategoryDAOControllerImpl() {
        super(Category.class, String.class);
    }


    @Override
    public List<Category> getAllCustomersByPagination(Integer limit, Integer offset) {

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
    public List<Category> getAllCus() {
        Criteria criteria = getSession().createCriteria(entityType);
        criteria.addOrder(org.hibernate.criterion.Order.desc("sortingValue"));
        return criteria.list();
    }

    @Override
    public List<Category> getAll(String branchid,Integer limit, Integer offset) {
        Criteria criteria = getSession().createCriteria(entityType);
        criteria.add(Restrictions.eq("branchid", branchid));

        if (limit!=null&&offset!=null) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
        }
        return criteria.list();
    }





}
