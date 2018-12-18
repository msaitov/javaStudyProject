package ru.msaitov.practice.service.office;

import ru.msaitov.practice.view.OfficeView;

import java.util.List;

/**
 * Сервис Office
 */
public interface OfficeService {

    /**
     * Получить список объектов Office и смапить на OfficeView
     * Все поля officeView не равны null будут служить фильром для возврата списка объектов OfficeView
     *
     * @param officeView
     * @return
     */
    List<OfficeView> filter(OfficeView officeView);

    /**
     * Получить Office по идентификатору и смапить на OfficeView
     *
     * @param id
     * @return
     */
    OfficeView loadById(Long id);

    /**
     * Обновление Office
     *
     * @param officeView
     * @return
     */
    String update(OfficeView officeView);

    /**
     * Сохранение Office
     *
     * @param officeView
     * @return
     */
    String save(OfficeView officeView);


}
