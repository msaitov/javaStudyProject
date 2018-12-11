package ru.msaitov.practice.dao.office;

import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.view.OfficeView;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить список объектов Office из БД
     * Все поля officeView не равны null будут служить фильром для возврата объектов Office
     *
     * @param officeView
     * @return
     */
    List<Office> getItems(OfficeView officeView);

    /**
     * Получить Office по идентификатору из БД
     *
     * @param id
     * @return
     */
    Office getItemById(Long id);

}
