package ru.msaitov.practice.service.employee;

import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

/**
 * Сервис Employee
 */
public interface EmployeeService {

    /**
     * Получить список объектов Employee и смапить на EmployeeView
     * Все поля employeeView не равны null будут служить фильром для возврата списка объектов EmployeeView
     *
     * @param employeeView
     * @return
     */
    List<EmployeeView> filter(EmployeeView employeeView);

    /**
     * Получить Employee по идентификатору и смапить на EmployeeView
     *
     * @param id
     * @return
     */
    EmployeeView loadById(Long id);
}
