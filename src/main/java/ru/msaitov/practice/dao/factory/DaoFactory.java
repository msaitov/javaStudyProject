package ru.msaitov.practice.dao.factory;

import ru.msaitov.practice.dao.employee.EmployeeDao;
import ru.msaitov.practice.dao.office.OfficeDao;
import ru.msaitov.practice.dao.organization.OrganizationDao;

/**
 * Абстрактная фабрика Dao
 */
public interface DaoFactory {

    /**
     * Получить Dao Organization
     *
     * @return
     */
    OrganizationDao getOrganizationDao();

    /**
     * Получить Dao Office
     *
     * @return
     */
    OfficeDao getOfficeDao();

    /**
     * Получить Dao Employee
     *
     * @return
     */
    EmployeeDao getEmployeeDao();
}
