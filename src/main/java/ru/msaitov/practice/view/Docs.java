package ru.msaitov.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Вывод справочника Docs
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Docs {

    /**
     * тип документа
     */
    private String name;

    /**
     * код документа
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
