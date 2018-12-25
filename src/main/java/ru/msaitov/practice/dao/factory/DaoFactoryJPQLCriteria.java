package ru.msaitov.practice.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import ru.msaitov.practice.dao.employee.EmployeeDao;
import ru.msaitov.practice.dao.employee.EmployeeDaoImpl;
import ru.msaitov.practice.dao.office.OfficeDao;
import ru.msaitov.practice.dao.office.OfficeDaoImpl;
import ru.msaitov.practice.dao.organization.OrganizationDao;
import ru.msaitov.practice.dao.organization.OrganizationDaoImpl;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
public class DaoFactoryJPQLCriteria implements DaoFactory {

    private final EntityManager em;

    @Autowired
    public DaoFactoryJPQLCriteria(final EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationDao getOrganizationDao() {
        return new OrganizationDaoImpl(em);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeDao getOfficeDao() {
        return new OfficeDaoImpl(em);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl(em);
    }
}
