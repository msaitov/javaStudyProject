package ru.msaitov.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Вывод данных OrganizationView
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * наименование организации
     */
    private String name;

    /**
     * полное название организации
     */
    private String nameFull;

    /**
     * адрес
     */
    private String address;

    /**
     * ИНН организации
     */
    private String inn;

    /**
     * КПП организации
     */
    private String kpp;

    /**
     * телефон
     */
    private String phone;

    /**
     * Статус организации: true - активен, false - не активен
     */
    private Boolean isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
