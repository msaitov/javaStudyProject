package ru.msaitov.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Организация
 */
@Entity(name = "Organization")
public class Organization {

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
     * имя организации
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * полное имя организации
     */
    @Column(name = "name_full", length = 50, nullable = false)
    private String nameFull;

    /**
     * адрес организации
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * ИНН организации
     */
    @Column(name = "inn", length = 30, nullable = false, unique = true)
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", length = 30, nullable = false, unique = true)
    private String kpp;

    /**
     * телефон организации
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * статус организации: true - активен, false - не активен'
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Конструктор для hibernate
     */
    public Organization() {
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

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
