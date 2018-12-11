package ru.msaitov.practice.dao.organization;

import ru.msaitov.practice.model.Organization;
import ru.msaitov.practice.view.OrganizationView;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить список объектов Organization из БД
     * Все поля organizationView не равны null будут служить фильром для возврата объектов Organization
     *
     * @param organizationView
     * @return
     */
    List<Organization> getItems(OrganizationView organizationView);

    /**
     * Получить Organization по идентификатору из БД
     *
     * @param id
     * @return
     */
    Organization getItemById(Long id);
}
