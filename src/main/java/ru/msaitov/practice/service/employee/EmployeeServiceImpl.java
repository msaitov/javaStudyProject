package ru.msaitov.practice.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msaitov.practice.dao.employee.EmployeeDao;
import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.model.mapper.employee.MapperEmployee;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final MapperEmployee mapperEmployee;

    @Autowired
    public EmployeeServiceImpl(final EmployeeDao employeeDao, final MapperEmployee mapperEmployee) {
        this.employeeDao = employeeDao;
        this.mapperEmployee = mapperEmployee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeView> filter(final EmployeeView employeeView) {

        if (employeeView.getOfficeId() == null) {
            return null;
        }

        Employee employee = mapperEmployee.map(employeeView);

        List<Employee> employeeList = employeeDao.getItems(employee);

        List<EmployeeView> employeeViewList = mapperEmployee.mapAsList(employeeList);

        for (EmployeeView ev : employeeViewList) {
            ev.setOfficeId(null);
            ev.setPhone(null);
            ev.setDocNumber(null);
            ev.setDocDate(null);
            ev.setDocCodeNum(null);
            ev.setCitizenshipCode(null);
            ev.setIsIdentified(null);
            ev.setDocName(null);
            ev.setCitizenshipName(null);
        }
        return employeeViewList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public EmployeeView loadById(final Long id) {
        Employee employee = employeeDao.getItemById(id);
        EmployeeView employeeView = mapperEmployee.map(employee);
        if (employeeView == null) {
            return null;
        }
        employeeView.setOfficeId(null);
        return employeeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String update(final EmployeeView employeeView) {
        Employee employee = mapperEmployee.map(employeeView);
        return employeeDao.updateItem(employee);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String save(final EmployeeView employeeView) {
        Employee employee = mapperEmployee.map(employeeView);
        return employeeDao.add(employee);
    }
}
