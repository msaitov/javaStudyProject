package ru.msaitov.practice.dao.organization;

import ru.msaitov.practice.model.Organization;

import java.util.List;

/**
 * DAO для работы с organization
 */
public interface OrganizationDao {

    /**
     * Получить список объектов organization из БД
     * Все поля объекта organization (который в аргументе метода) не равные null будут служить фильром для возврата списка объектов List<organization>
     *
     * @param organization
     * @return
     */
    List<Organization> getItems(Organization organization);

    /**
     * Получить organization по идентификатору из БД
     *
     * @param id
     * @return
     */
    Organization getItemById(Long id);

    /**
     * Обновление Organization в базе данных
     *
     * @param organization
     * @return
     */
    String updateItem(Organization organization);

    /**
     * Добавление Organization в базу данных
     *
     * @param organization
     * @return
     */
    String add(Organization organization);

}
