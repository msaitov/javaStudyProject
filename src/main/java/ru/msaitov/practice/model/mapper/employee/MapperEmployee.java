package ru.msaitov.practice.model.mapper.employee;

import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

/**
 * Маппер с класса Employee на класс EmployeeView
 */
public interface MapperEmployee {

    /**
     * Маппер List классов
     */
    List<EmployeeView> mapAsList(List<Employee> employeeList);

    /**
     * Маппер Single классов
     */
    EmployeeView map(Employee employeeList);
}
