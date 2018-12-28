package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.controller.handleException.CustomNotFoundException;
import ru.msaitov.practice.service.employee.EmployeeService;
import ru.msaitov.practice.view.Countries;
import ru.msaitov.practice.view.Docs;
import ru.msaitov.practice.view.EmployeeView;
import ru.msaitov.practice.view.general.GeneralView;
import ru.msaitov.practice.view.general.ResponseView;

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
    @PostMapping("api/user/list")
    public GeneralView employeeList(@RequestBody final EmployeeView employeeView) {
        if (employeeView.getOfficeId() == null) {
            throw new CustomNotFoundException("Поле officeId обязательно к заполнению");
        }
        return new GeneralView<List<EmployeeView>>().setData(employeeService.filter(employeeView));
    }


    /**
     * Получить EmployeeView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/user/{id}")
    public GeneralView employeeId(@PathVariable final Long id) {
        EmployeeView employeeView = employeeService.loadById(id);
        if (employeeView == null) {
            throw new CustomNotFoundException("id не найдено");
        }
        return new GeneralView<EmployeeView>().setData(employeeView);

    }

    /**
     * Обновление Employee по id
     *
     * @param employeeView
     * @return
     */
    @PostMapping("api/user/update")
    public GeneralView employeeUpdate(@RequestBody final EmployeeView employeeView) {
        if (employeeView.getId() == null ||
                employeeView.getFirstName() == null ||
                employeeView.getPositionName() == null) {
            throw new CustomNotFoundException("Поля id, firstName, position обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(employeeService.update(employeeView)));
    }

    /**
     * Сохранить Employee
     * @param employeeView
     * @return
     */
    @PostMapping("api/user/save")
    public GeneralView employeeSave(@RequestBody final EmployeeView employeeView) {
        if (employeeView.getId() != null) {
            throw new CustomNotFoundException("Поле id должно быть пустым, т.к. генерируется автоматически");
        }
        if (employeeView.getOfficeId() == null ||
                employeeView.getFirstName() == null ||
                employeeView.getPositionName() == null) {
            throw new CustomNotFoundException("Поля officeId, firstName, position обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(employeeService.save(employeeView)));
    }

    /**
     * Справочник Тип документов
     *
     * @return
     */
    @GetMapping("api/docs")
    public GeneralView employeeDocs() {
        return new GeneralView<List<Docs>>().setData(employeeService.getDocs());
    }

    /**
     * Справочник Гражданство
     *
     * @return
     */
    @GetMapping("api/countries")
    public GeneralView employeeСountries() {
        return new GeneralView<List<Countries>>().setData(employeeService.getСountries());
    }


}
