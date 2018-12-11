package ru.msaitov.practice.service.organization;

import ru.msaitov.practice.view.OrganizationView;

import java.util.List;

/**
 * Сервис Organization
 */
public interface OrganizationService {

    /**
     * Получить список объектов Organization и смапить на OrganizationView
     * Все поля organizationView не равны null будут служить фильром для возврата списка объектов OrganizationView
     *
     * @param organizationView
     * @return
     */
    List<OrganizationView> filter(OrganizationView organizationView);

    /**
     * Получить Organization по идентификатору и смапить на OrganizationView
     *
     * @param id
     * @return
     */
    OrganizationView loadById(Long id);
}
