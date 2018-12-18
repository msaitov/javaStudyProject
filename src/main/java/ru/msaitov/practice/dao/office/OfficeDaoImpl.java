package ru.msaitov.practice.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.model.Office_;
import ru.msaitov.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(final EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getItems(final Office office) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> officeRoot = cq.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();

        Long orgId = office.getOrganization().getId();
        if (orgId != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.organization), orgId));
        }
        if (office.getName() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.name), office.getName()));
        }
        if (office.getPhone() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.phone), office.getPhone()));
        }
        if (office.getIsActive() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.isActive), office.getIsActive()));
        }

        cq.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Office> query = em.createQuery(cq);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getItemById(final Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> organizationRoot = cq.from(Office.class);

        cq.where(cb.equal(organizationRoot.get(Office_.id), id));
        TypedQuery<Office> q = em.createQuery(cq);
        Office office = null;
        try {
            office = q.getSingleResult();
        } catch (NoResultException e) {

        }
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(Office office) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Office> crUpdate = cb.createCriteriaUpdate(Office.class);
        Root<Office> organizationRoot = crUpdate.from(Office.class);

        crUpdate.set(Office_.name, office.getName());
        crUpdate.set(Office_.address, office.getAddress());

        Long organizationId = office.getOrganization().getId();
        if (organizationId != null) {
            Organization organization = em.find(Organization.class, organizationId);
            crUpdate.set(Office_.organization, organization);
        }

        if (office.getPhone() != null) {
            crUpdate.set(Office_.phone, office.getPhone());
        }

        if (office.getIsActive() != null) {
            crUpdate.set(Office_.isActive, office.getIsActive());
        }

        crUpdate.where(cb.equal(organizationRoot.get(Office_.id), office.getId()));

        String result;
        int resultInt = em.createQuery(crUpdate).executeUpdate();
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
    public String add(Office office) {
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
