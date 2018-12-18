package ru.msaitov.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Вывод данных EmployeeView
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Идентификатор Организации
     */
    @JsonProperty("orgId")
    private Long organizationId;

    /**
     * наименование Офиса
     */
    private String name;

    /**
     * адрес
     */
    private String address;

    /**
     * телефон
     */
    private String phone;

    /**
     * Статус офиса: true - активен, false - не активен
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
