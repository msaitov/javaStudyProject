package ru.msaitov.practice.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.model.Office_;
import ru.msaitov.practice.view.OfficeView;

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
    public List<Office> getItems(final OfficeView officeView) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> officeRoot = cq.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();

        if (officeView.getOrganizationId() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.organization), officeView.getOrganizationId()));
        }
        if (officeView.getName() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.name), officeView.getName()));
        }
        if (officeView.getPhone() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.phone), officeView.getPhone()));
        }
        if (officeView.getIsActive() != null) {
            predicates.add(cb.equal(officeRoot.get(Office_.isActive), officeView.getIsActive()));
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
        return q.getSingleResult();
    }
}
