package ru.msaitov.practice.view.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Главный класс вывода данных
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralView<T> {

    private T data;

    public T getData() {
        return data;
    }

    public GeneralView setData(T data) {
        this.data = data;
        return this;
    }
}