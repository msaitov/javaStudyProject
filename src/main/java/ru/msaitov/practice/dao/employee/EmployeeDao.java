package ru.msaitov.practice.dao.employee;

import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;


/**
 * DAO для работы с Employee
 */
public interface EmployeeDao {

    /**
     * Получить список объектов Employee из БД
     * Все поля employeeView не равны null будут служить фильром для возврата объектов Employee
     *
     * @param employeeView
     * @return
     */
    List<Employee> getItems(EmployeeView employeeView);

    /**
     * Получить Employee по идентификатору из БД
     *
     * @param id
     * @return
     */
    Employee getItemById(Long id);
}
