package ru.msaitov.practice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.msaitov.practice.view.DataViewList;
import ru.msaitov.practice.view.DataViewSingle;

import java.util.List;
import java.util.Map;

/**
 * Получить данные по url
 */
public class GetDataByUrl<T> {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    /**
     * GET Запрос который получает одиночный объект
     * @param url
     * @param typeParameterClass Тип получаемых данных
     * @return
     */
    public T getSingle(final String url, final ParameterizedTypeReference<DataViewSingle<T>> typeParameterClass) {
        init();
        ResponseEntity<DataViewSingle<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeParameterClass);
        DataViewSingle<T> dataViewSingle = responseEntity.getBody();
        T tSingle = dataViewSingle.getData();
        return tSingle;
    }

    /**
     * GET Запрос который получает список
     *
     * @param url
     * @param typeParameterClass Тип получаемых данных
     * @return
     */
    public List<T> getListQueryGet(final String url, final ParameterizedTypeReference<DataViewList<T>> typeParameterClass) {
        init();
        ResponseEntity<DataViewList<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeParameterClass);
        DataViewList<T> dataViewList = responseEntity.getBody();
        List<T> tList = dataViewList.getData();
        return tList;
    }



    /**
     * Запрос который получает список объектов
     *
     * @param url
     * @param object             данные для отправки post запроса
     * @param typeParameterClass Тип получаемых данных
     * @return
     */
    public List<T> getList(final String url, final T object, final ParameterizedTypeReference<DataViewList<T>> typeParameterClass) {
        init();
        HttpEntity<Object> requestEntity = new HttpEntity<>(object, headers);
        ResponseEntity<DataViewList<T>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, typeParameterClass);
        DataViewList<T> dataViewList = responseEntity.getBody();
        List<T> tList = dataViewList.getData();
        return tList;
    }

    /**
     * Запрос который получает статус сделайнной операции
     *
     * @param url
     * @param object данные для отправки post запроса
     * @return
     */
    public Map getStatus(final String url, final T object) {
        init();
        HttpEntity<Object> requestEntity = new HttpEntity<>(object, headers);
        ResponseEntity<DataViewSingle<T>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<DataViewSingle<T>>() {
        });
        return (Map) responseEntity.getBody().getData();
    }

    private void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

}
