package ru.msaitov.practice.dao.organization;

import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.Organization;
import ru.msaitov.practice.model.Organization_;

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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getItems(final Organization organization) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = cq.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();

        if (organization.getName() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.name), organization.getName()));
        }
        if (organization.getInn() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.inn), organization.getInn()));
        }
        if (organization.getIsActive() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.isActive), organization.getIsActive()));
        }

        cq.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Organization> query = em.createQuery(cq);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getItemById(final Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = cq.from(Organization.class);

        cq.where(cb.equal(organizationRoot.get(Organization_.id), id));
        TypedQuery<Organization> q = em.createQuery(cq);
        Organization organization = null;
        try {
            organization = q.getSingleResult();
        } catch (NoResultException e) {

        }
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(Organization organization) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Organization> crUpdate = cb.createCriteriaUpdate(Organization.class);
        Root<Organization> organizationRoot = crUpdate.from(Organization.class);

        crUpdate.set(Organization_.name, organization.getName());
        crUpdate.set(Organization_.nameFull, organization.getNameFull());
        crUpdate.set(Organization_.inn, organization.getInn());
        crUpdate.set(Organization_.kpp, organization.getKpp());
        crUpdate.set(Organization_.address, organization.getAddress());
        if (organization.getPhone() != null) {
            crUpdate.set(Organization_.phone, organization.getPhone());
        }
        if (organization.getIsActive() != null) {
            crUpdate.set(Organization_.isActive, organization.getIsActive());
        }

        crUpdate.where(cb.equal(organizationRoot.get(Organization_.id), organization.getId()));

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
    public String add(Organization organization) {
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
