package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.service.office.OfficeService;
import ru.msaitov.practice.view.OfficeView;

import java.util.List;

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
    public List<OfficeView> officeList(@RequestBody final OfficeView officeView) {
        return officeService.filter(officeView);
    }

    /**
     * Получить OfficeView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/office/{id}")
    public OfficeView officeId(@PathVariable final Long id) {
        return officeService.loadById(id);
    }
}
