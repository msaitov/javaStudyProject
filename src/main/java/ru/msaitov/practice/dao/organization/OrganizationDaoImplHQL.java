package ru.msaitov.practice.dao.organization;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.dao.additionalFunctions.AddingQueryString;
import ru.msaitov.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImplHQL implements OrganizationDao {

    private final EntityManager em;
    private final AddingQueryString addingQS;

    @Autowired
    public OrganizationDaoImplHQL(final EntityManager em, final AddingQueryString addingQSW) {
        this.em = em;
        this.addingQS = addingQSW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getItems(final Organization organization) {
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Organization org";

        addingQS.clear();
        addingQS.setFirstWord("WHERE");
        addingQS.setSeparator("AND");
        addingQS.setTableName("org");
        if (organization.getName() != null) {
            addingQS.add("name");
        }
        if (organization.getInn() != null) {
            addingQS.add("inn");
        }
        if (organization.getIsActive() != null) {
            addingQS.add("isActive");
        }


        strQuary += addingQS.generateString();
        Query query = session.createQuery(strQuary).setProperties(organization);
        List<Organization> list = query.list();
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getItemById(final Long id) {

        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Organization org WHERE org.id = :id";
        Query query = session.createQuery(strQuary).setParameter("id", id);
        Organization organization = null;
        try {
            organization = (Organization) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(final Organization organization) {
        Session session = em.unwrap(Session.class);
        String strQuary = "UPDATE Organization org";

        addingQS.clear();
        addingQS.setFirstWord("SET");
        addingQS.setSeparator(",");
        addingQS.setTableName("org");
        addingQS.add("name");
        addingQS.add("nameFull");
        addingQS.add("inn");
        addingQS.add("kpp");
        addingQS.add("address");

        if (organization.getPhone() != null) {
            addingQS.add("phone");
        }
        if (organization.getIsActive() != null) {
            addingQS.add("isActive");
        }

        strQuary += addingQS.generateString();
        strQuary += "WHERE org.id = :id";

        Query query = session.createQuery(strQuary).setParameter("id", organization.getId());
        query.setProperties(organization);
        int resultInt = query.executeUpdate();
        String result;
        if (resultInt >= 1) {
            result = "success";
        } else {
            result = "failure";
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String add(final Organization organization) {
        em.persist(organization);
        String result;
        if (organization.getId() != null) {
            result = "success";
        } else {
            result = "failure";
        }
        return result;
    }
}
