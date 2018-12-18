package ru.msaitov.practice.service.organization;

import ru.msaitov.practice.view.OrganizationView;

import java.util.List;

/**
 * Сервис organization
 */
public interface OrganizationService {

    /**
     * Получить список объектов organization и смапить на OrganizationView
     * Все поля organizationView не равны null будут служить фильром для возврата списка объектов OrganizationView
     *
     * @param organizationView
     * @return
     */
    List<OrganizationView> filter(OrganizationView organizationView);

    /**
     * Получить organization по идентификатору и смапить на OrganizationView
     *
     * @param id
     * @return
     */
    OrganizationView loadById(Long id);

    /**
     * Обновление Organization
     *
     * @param organizationView
     * @return
     */
    String update(OrganizationView organizationView);

    /**
     * Сохранение Organization
     *
     * @param organizationView
     * @return
     */
    String save(OrganizationView organizationView);
}
