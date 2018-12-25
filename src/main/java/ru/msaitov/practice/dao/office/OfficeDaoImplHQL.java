package ru.msaitov.practice.dao.office;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.dao.additionalFunctions.AddingQueryString;
import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OfficeDaoImplHQL implements OfficeDao {

    private final EntityManager em;
    private final AddingQueryString addingQS;


    @Autowired
    public OfficeDaoImplHQL(final EntityManager em, final AddingQueryString addingQSW) {
        this.em = em;
        this.addingQS = addingQSW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getItems(final Office office) {
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Office office";

        addingQS.clear();
        addingQS.setFirstWord("WHERE");
        addingQS.setSeparator("AND");
        addingQS.setTableName("office");

        addingQS.setO("organizationId");
        addingQS.add("organization.id");

        if (office.getName() != null) {
            addingQS.add("name");
        }
        if (office.getPhone() != null) {
            addingQS.add("phone");
        }
        if (office.getIsActive() != null) {
            addingQS.add("isActive");
        }

        strQuary += addingQS.generateString();
        Query query = session.createQuery(strQuary)
                .setProperties(office)
                .setParameter("organizationId", office.getOrganization().getId());


        List<Office> list = query.list();
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getItemById(final Long id) {
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Office org WHERE org.id = :id";
        Query query = session.createQuery(strQuary).setParameter("id", id);
        Office office = null;
        try {
            office = (Office) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(final Office office) {
        Session session = em.unwrap(Session.class);
        String strQuary = "UPDATE Office office";

        addingQS.clear();
        addingQS.setFirstWord("SET");
        addingQS.setSeparator(",");
        addingQS.setTableName("office");
        addingQS.add("name");
        addingQS.add("address");

        Long organizationId = office.getOrganization().getId();
        if (organizationId != null) {
            addingQS.add("organization");
        }
        if (office.getPhone() != null) {
            addingQS.add("phone");
        }
        if (office.getIsActive() != null) {
            addingQS.add("isActive");
        }

        strQuary += addingQS.generateString();
        strQuary += "WHERE office.id = :id";

        Query query = session.createQuery(strQuary).setParameter("id", office.getId());
        query.setProperties(office);
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
    public String add(final Office office) {
        Organization organization = em.find(Organization.class, office.getOrganization().getId());
        office.setOrganization(organization);
        em.persist(office);
        String result;
        if (office.getId() != null) {
            result = "success";
        } else {
            result = "failure";
        }
        return result;
    }
}
