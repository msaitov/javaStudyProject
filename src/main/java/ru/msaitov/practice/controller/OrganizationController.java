package ru.msaitov.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.practice.service.organization.OrganizationService;
import ru.msaitov.practice.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер API для работы с Organization
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
    public List<OrganizationView> organizationList(@RequestBody final OrganizationView organizationView) {
        return orgServ.filter(organizationView);
    }

    /**
     * Получить OrganizationView по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("api/organization/{id}")
    public OrganizationView organizationId(@PathVariable final Long id) {
        return orgServ.loadById(id);
    }
}
