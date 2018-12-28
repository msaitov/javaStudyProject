package ru.msaitov.practice.dao.employee;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.dao.additionalFunctions.AddingQueryString;
import ru.msaitov.practice.model.employee.Citizenship;
import ru.msaitov.practice.model.employee.DocCode;
import ru.msaitov.practice.model.employee.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class EmployeeDaoImplHQL implements EmployeeDao {

    private final EntityManager em;
    private final AddingQueryString addingQS;

    @Autowired
    public EmployeeDaoImplHQL(final EntityManager em, final AddingQueryString addingQSW) {
        this.em = em;
        this.addingQS = addingQSW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getItems(final Employee employee) {

        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Employee employee";

        addingQS.clear();
        addingQS.setFirstWord("WHERE");
        addingQS.setSeparator("AND");
        addingQS.setTableName("employee");

        addingQS.add("office");

        if (employee.getFirstName() != null) {
            addingQS.add("firstName");
        }
        if (employee.getLastName() != null) {
            addingQS.add("lastName");
        }
        if (employee.getMiddleName() != null) {
            addingQS.add("middleName");
        }
        if (employee.getPosition() != null) {
            addingQS.add("position");
        }

        if (employee.getDocCode() != null) {
            addingQS.add("docCode");
        }
        if (employee.getCitizenship() != null) {
            addingQS.add("citizenship");
        }

        strQuary += addingQS.generateString();
        Query query = session.createQuery(strQuary).setProperties(employee);
        List list = query.list();
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getItemById(final Long id) {
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Employee employee WHERE employee.id = :id";
        Query query = session.createQuery(strQuary).setParameter("id", id);
        Employee employee = null;
        try {
            employee = (Employee) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateItem(final Employee employee) {
        Session session = em.unwrap(Session.class);
        String strQuary = "UPDATE Employee employee";

        addingQS.clear();
        addingQS.setFirstWord("SET");
        addingQS.setSeparator(",");
        addingQS.setTableName("employee");
        addingQS.add("firstName");
        addingQS.add("position");

        if (employee.getOffice() != null) {
            addingQS.add("office");
        }
        if (employee.getLastName() != null) {
            addingQS.add("lastName");
        }
        if (employee.getMiddleName() != null) {
            addingQS.add("middleName");
        }
        if (employee.getPhone() != null) {
            addingQS.add("phone");
        }
        if (employee.getDocCode() != null) {
            addingQS.add("docCode");
        }
        if (employee.getDocDate() != null) {
            addingQS.add("docDate");
        }
        if (employee.getCitizenship() != null) {
            addingQS.add("citizenship");
        }
        if (employee.getIsIdentified() != null) {
            addingQS.add("isIdentified");
        }

        strQuary += addingQS.generateString();
        strQuary += "WHERE employee.id = :id";

        Query query = session.createQuery(strQuary).setParameter("id", employee.getId());
        query.setProperties(employee);
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
    public String add(final Employee employee) {

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
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM DocCode";
        Query query = session.createQuery(strQuary);
        List docCodeList = query.getResultList();
        return docCodeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Citizenship> getСountries() {
        Session session = em.unwrap(Session.class);
        String strQuary = "FROM Citizenship";
        Query query = session.createQuery(strQuary);
        List citizenshipList = query.getResultList();
        return citizenshipList;
    }
}
