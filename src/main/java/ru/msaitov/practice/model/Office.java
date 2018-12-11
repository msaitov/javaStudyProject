package ru.msaitov.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * Офис
 */
@Entity(name = "Office")
public class Office {

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
     * Связь: много Офисов к одной Организации
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * имя офиса
     */
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    /**
     * адрес Офиса
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * телефон Офиса
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * Статус офиса: true - активен, false - не активен
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
