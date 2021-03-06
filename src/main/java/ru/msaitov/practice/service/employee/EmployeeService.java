package ru.msaitov.practice.service.employee;

import ru.msaitov.practice.view.Countries;
import ru.msaitov.practice.view.Docs;
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

    /**
     * Обновление Employee
     *
     * @param employeeView
     * @return
     */
    String update(EmployeeView employeeView);

    /**
     * Сохранение Employee
     * @param employeeView
     * @return
     */
    String save(EmployeeView employeeView);

    /**
     * Получение справочника тип документов
     *
     * @return
     */
    List<Docs> getDocs();

    /**
     * Получение справочника Гражданство
     *
     * @return
     */
    List<Countries> getСountries();

}
