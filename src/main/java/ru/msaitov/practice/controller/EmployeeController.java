package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.service.employee.EmployeeService;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер API для работы с Employee
 */
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Получить список объектов EmployeeView
     *
     * @param employeeView
     * @return
     */
    @PostMapping("api/employee/list")
    public List<EmployeeView> employeeList(@RequestBody final EmployeeView employeeView) {
        return employeeService.filter(employeeView);
    }


    /**
     * Получить EmployeeView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/employee/{id}")
    public EmployeeView employeeId(@PathVariable final Long id) {
        return employeeService.loadById(id);
    }
}
