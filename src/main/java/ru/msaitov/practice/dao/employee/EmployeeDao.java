package ru.msaitov.practice.dao.employee;

import ru.msaitov.practice.model.employee.Employee;

import java.util.List;

/**
 * DAO для работы с Employee
 */
public interface EmployeeDao {

    /**
     * Получить список объектов Employee из БД
     * Все поля объекта employee (который в аргументе метода) не равные null будут служить фильром для возврата списка объектов List<Employee>
     * @param employee
     * @return
     */
    List<Employee> getItems(Employee employee);

    /**
     * Получить Employee по идентификатору из БД
     * @param id
     * @return
     */
    Employee getItemById(Long id);

    /**
     * Обновление Employee в базе данных
     *
     * @param employee
     * @return
     */
    String updateItem(Employee employee);

    /**
     * Добавление Employee в базу данных
     *
     * @param employee
     * @return
     */
    String add(Employee employee);

}
