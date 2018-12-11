package ru.msaitov.practice.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.Organization;
import ru.msaitov.practice.model.Organization_;
import ru.msaitov.practice.view.OrganizationView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    @Autowired
    public OrganizationDaoImpl(final EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getItems(final OrganizationView organizationView) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = cq.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();

        if (organizationView.getName() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.name), organizationView.getName()));
        }
        if (organizationView.getInn() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.inn), organizationView.getInn()));
        }
        if (organizationView.getIsActive() != null) {
            predicates.add(cb.equal(organizationRoot.get(Organization_.isActive), organizationView.getIsActive()));
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
        return q.getSingleResult();
    }
}
