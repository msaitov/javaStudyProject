package ru.msaitov.practice.model.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Должность
 */
@Entity(name = "Position")
public class Position {

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
     * наименование должности
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Конструктор для hibernate
     */
    public Position() {
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
}
