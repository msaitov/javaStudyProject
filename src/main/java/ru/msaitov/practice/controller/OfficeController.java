package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.controller.handleException.CustomNotFoundException;
import ru.msaitov.practice.service.office.OfficeService;
import ru.msaitov.practice.view.OfficeView;
import ru.msaitov.practice.view.general.GeneralView;
import ru.msaitov.practice.view.general.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер API для работы с Office
 */
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(final OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получить список объектов OfficeView
     *
     * @param officeView
     * @return
     */
    @PostMapping("api/office/list")
    public GeneralView officeList(@RequestBody final OfficeView officeView) {
        if (officeView.getOrganizationId() == null) {
            throw new CustomNotFoundException("Поле orgId обязательно к заполнению");
        }
        return new GeneralView().setData(officeService.filter(officeView));
    }

    /**
     * Получить OfficeView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/office/{id}")
    public GeneralView officeId(@PathVariable final Long id) {
        OfficeView officeView = officeService.loadById(id);
        if (officeView == null) {
            throw new CustomNotFoundException("id не найдено");
        }
        return new GeneralView().setData(officeView);
    }

    /**
     * Обновление Office по id
     *
     * @param officeView
     * @return
     */
    @PostMapping("api/office/update")
    public GeneralView officeUpdate(@RequestBody final OfficeView officeView) {
        if (officeView.getId() == null ||
                officeView.getName() == null ||
                officeView.getAddress() == null) {
            throw new CustomNotFoundException("Поля id, name, address обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(officeService.update(officeView)));
    }

    /**
     * Сохранить Office
     *
     * @param officeView
     * @return
     */
    @PostMapping("api/office/save")
    public GeneralView officeSave(@RequestBody final OfficeView officeView) {
        if (officeView.getId() != null) {
            throw new CustomNotFoundException("Поле id должно быть пустым, т.к. генерируется автоматически");
        }
        if (officeView.getOrganizationId() == null ||
                officeView.getName() == null ||
                officeView.getAddress() == null) {
            throw new CustomNotFoundException("Поля organizationId, name, address обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(officeService.save(officeView)));
    }
}
