package ru.msaitov.practice.dao.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.employee.Citizenship;
import ru.msaitov.practice.model.employee.Citizenship_;
import ru.msaitov.practice.model.employee.DocCode;
import ru.msaitov.practice.model.employee.DocCode_;
import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.model.employee.Employee_;
import ru.msaitov.practice.model.employee.Position_;

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
    public List<Employee> getItems(final Employee employee) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        Long officeId = employee.getOffice().getId();
        if (officeId != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.office), officeId));
        }
        if (employee.getFirstName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.firstName), employee.getFirstName()));
        }
        if (employee.getLastName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.lastName), employee.getLastName()));
        }
        if (employee.getMiddleName() != null) {
            predicates.add(cb.equal(employeeRoot.get(Employee_.middleName), employee.getMiddleName()));
        }
        if (employee.getPosition() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.position).get(Position_.name), employee.getPosition().getName()));
        }
        if (employee.getDocCode() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.docCode).get(DocCode_.code), employee.getDocCode().getCode()));
        }
        if (employee.getCitizenship() != null) {
            predicates.add(cb.equal(employeeRoot.join(Employee_.citizenship).get(Citizenship_.code), employee.getCitizenship().getCode()));
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
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        } catch (NoResultException e) {

        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(Employee employee) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Employee> crUpdate = cb.createCriteriaUpdate(Employee.class);
        Root<Employee> employeeRoot = crUpdate.from(Employee.class);

        crUpdate.set(Employee_.firstName, employee.getFirstName());
        crUpdate.set(Employee_.position, employee.getPosition());

        if (employee.getLastName() != null) {
            crUpdate.set(Employee_.lastName, employee.getLastName());
        }
        if (employee.getMiddleName() != null) {
            crUpdate.set(Employee_.middleName, employee.getMiddleName());
        }
        if (employee.getPhone() != null) {
            crUpdate.set(Employee_.phone, employee.getPhone());
        }

        if (employee.getOffice() != null) {
            crUpdate.set(Employee_.office, employee.getOffice());
        }

        if (employee.getDocCode() != null) {
            crUpdate.set(Employee_.docCode, employee.getDocCode());
        }

        if (employee.getDocNumber() != null) {
            crUpdate.set(Employee_.docNumber, employee.getDocNumber());
        }

        if (employee.getDocDate() != null) {
            crUpdate.set(Employee_.docDate, employee.getDocDate());
        }

        if (employee.getCitizenship() != null) {
            crUpdate.set(Employee_.citizenship, employee.getCitizenship());
        }

        if (employee.getIsIdentified() != null) {
            crUpdate.set(Employee_.isIdentified, employee.getIsIdentified());
        }

        crUpdate.where(cb.equal(employeeRoot.get(Employee_.id), employee.getId()));

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
    public String add(Employee employee) {

        em.persist(employee);
        String result;
        if (employee.getId() != null) {
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
    public List<DocCode> getDocs() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocCode> cq = cb.createQuery(DocCode.class);
        cq.from(DocCode.class);
        TypedQuery<DocCode> query = em.createQuery(cq);
        List<DocCode> docCodeList = query.getResultList();
        return docCodeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Citizenship> getСountries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Citizenship> cq = cb.createQuery(Citizenship.class);
        cq.from(Citizenship.class);
        TypedQuery<Citizenship> query = em.createQuery(cq);
        List<Citizenship> citizenshipList = query.getResultList();
        return citizenshipList;
    }
}
