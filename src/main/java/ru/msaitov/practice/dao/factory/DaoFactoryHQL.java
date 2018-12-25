package ru.msaitov.practice.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import ru.msaitov.practice.dao.additionalFunctions.AddingQueryString;
import ru.msaitov.practice.dao.employee.EmployeeDao;
import ru.msaitov.practice.dao.employee.EmployeeDaoImplHQL;
import ru.msaitov.practice.dao.office.OfficeDao;
import ru.msaitov.practice.dao.office.OfficeDaoImplHQL;
import ru.msaitov.practice.dao.organization.OrganizationDao;
import ru.msaitov.practice.dao.organization.OrganizationDaoImplHQL;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
public class DaoFactoryHQL implements DaoFactory {

    private final EntityManager em;
    private final AddingQueryString addingQS;

    @Autowired
    public DaoFactoryHQL(final EntityManager em, final AddingQueryString addingQS) {
        this.em = em;
        this.addingQS = addingQS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationDao getOrganizationDao() {
        return new OrganizationDaoImplHQL(em, addingQS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeDao getOfficeDao() {
        return new OfficeDaoImplHQL(em, addingQS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImplHQL(em, addingQS);
    }
}
