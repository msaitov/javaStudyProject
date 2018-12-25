package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.controller.handleException.CustomNotFoundException;
import ru.msaitov.practice.service.organization.OrganizationService;
import ru.msaitov.practice.view.OrganizationView;
import ru.msaitov.practice.view.general.GeneralView;
import ru.msaitov.practice.view.general.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер API для работы с organization
 */
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService orgServ;

    @Autowired
    public OrganizationController(final OrganizationService orgServ) {
        this.orgServ = orgServ;
    }

    /**
     * Получить список объектов OrganizationView
     *
     * @param organizationView
     * @return
     */
    @PostMapping("/api/organization/list")
    public GeneralView organizationList(@RequestBody final OrganizationView organizationView) {
        if (organizationView.getName() == null) {
            throw new CustomNotFoundException("Поле Name обязательно к заполнению");
        }

        return new GeneralView().setData(orgServ.filter(organizationView));
    }

    /**
     * Получить OrganizationView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/organization/{id}")
    public GeneralView organizationId(@PathVariable final Long id) {
        OrganizationView organizationView = orgServ.loadById(id);
        if (organizationView == null) {
            throw new CustomNotFoundException("id не найдено");
        }
        return new GeneralView<OrganizationView>().setData(organizationView);
    }

    /**
     * Обновление organization по id
     *
     * @param organizationView
     * @return
     */
    @PostMapping("api/organization/update")
    public GeneralView organizationUpdate(@RequestBody final OrganizationView organizationView) {
        if (organizationView.getId() == null ||
                organizationView.getName() == null ||
                organizationView.getNameFull() == null ||
                organizationView.getInn() == null ||
                organizationView.getKpp() == null ||
                organizationView.getAddress() == null) {
            throw new CustomNotFoundException("Поля id, name, fullName, inn, kpp, address обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(orgServ.update(organizationView)));
    }

    /**
     * Сохранить organization
     *
     * @param organizationView
     * @return
     */
    @PostMapping("api/organization/save")
    public GeneralView organizationSave(@RequestBody final OrganizationView organizationView) {
        if (organizationView.getId() != null) {
            throw new CustomNotFoundException("Поле id должно быть пустым, т.к. генерируется автоматически");
        }
        if (organizationView.getName() == null ||
                organizationView.getNameFull() == null ||
                organizationView.getInn() == null ||
                organizationView.getKpp() == null ||
                organizationView.getAddress() == null) {
            throw new CustomNotFoundException("Поля name, nameFull, inn, kpp, address обязательны к заполнению");
        }
        return new GeneralView().setData(new ResponseView().setResult(orgServ.save(organizationView)));
    }
}
