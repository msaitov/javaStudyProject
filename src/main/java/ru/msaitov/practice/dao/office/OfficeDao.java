package ru.msaitov.practice.dao.office;

import ru.msaitov.practice.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить список объектов Office из БД
     * Все поля объекта office (который в аргументе метода) не равны null будут служить фильром для возврата списка объектов List<Office>
     *
     * @param office
     * @return
     */
    List<Office> getItems(Office office);

    /**
     * Получить Office по идентификатору из БД
     *
     * @param id
     * @return
     */
    Office getItemById(Long id);

    /**
     * Обновление Office в базе данных
     *
     * @param office
     * @return
     */
    String updateItem(Office office);

    /**
     * Добавление Office в базу данных
     *
     * @param office
     * @return
     */
    String add(Office office);

}
