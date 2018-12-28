package ru.msaitov.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Вывод справочника Гражданство
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Countries {

    /**
     * наименование гражданства
     */
    private String name;

    /**
     * код гражданства
     */
    private Long code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
