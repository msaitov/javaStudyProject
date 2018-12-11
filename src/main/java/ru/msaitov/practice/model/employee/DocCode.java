package ru.msaitov.practice.model.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Код-документа
 */
@Entity(name = "Doc_code")
public class DocCode {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Long version;

    /**
     * тип документа
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * код документа
     */
    @Column(name = "code", nullable = false, unique = true)
    private Long code;

    /**
     * Конструктор для hibernate
     */
    public DocCode() {
    }

    public Long getId() {
        return id;
    }

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
