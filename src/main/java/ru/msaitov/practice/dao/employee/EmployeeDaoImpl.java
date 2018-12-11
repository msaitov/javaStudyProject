package ru.msaitov.practice.dao.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.employee.Citizenship_;
import ru.msaitov.practice.model.employee.DocCode_;
import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.model.employee.Employee_;
import ru.msaitov.practice.model.employee.Position_;
import ru.msaitov.practice.view.EmployeeView;

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
public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager em;

    @Autowired
    public EmployeeDaoImpl(final EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getItems(final EmployeeView employeeView) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (employeeView.getOfficeId() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.office), employeeView.getOfficeId()));
        }
        if (employeeView.getFirstName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.firstName), employeeView.getFirstName()));
        }
        if (employeeView.getLastName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.lastName), employeeView.getLastName()));
        }
        if (employeeView.getMiddleName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.middleName), employeeView.getMiddleName()));
        }
        if (employeeView.getPositionName() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.position).get(Position_.name), employeeView.getPositionName()));
        }
        if (employeeView.getDocCodeNum() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.docCode).get(DocCode_.code), employeeView.getDocCodeNum()));
        }
        if (employeeView.getCitizenshipCode() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.citizenship).get(Citizenship_.code), employeeView.getCitizenshipCode()));
        }

        cq.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Employee> query = em.createQuery(cq);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getItemById(final Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> organizationRoot = cq.from(Employee.class);

        cq.where(cb.equal(organizationRoot.get(Employee_.id), id));
        TypedQuery<Employee> query = em.createQuery(cq);
        return query.getSingleResult();
    }
}
