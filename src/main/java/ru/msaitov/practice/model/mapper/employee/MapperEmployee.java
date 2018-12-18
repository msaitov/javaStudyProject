package ru.msaitov.practice.model.mapper.employee;

import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

/**
 * Маппер с класса Employee на класс EmployeeView и обратно
 */
public interface MapperEmployee {

    /**
     * Маппер List классов
     * @param employeeList
     * @return
     */
    List<EmployeeView> mapAsList(List<Employee> employeeList);

    /**
     * Маппер из Employee в EmployeeView
     * @param employee
     * @return
     */
    EmployeeView map(Employee employee);

    /**
     * Маппер из EmployeeView в Employee
     *
     * @param employeeView
     * @return
     */
    Employee map(EmployeeView employeeView);
}
