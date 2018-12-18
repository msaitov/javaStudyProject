package ru.msaitov.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Вывод данных EmployeeView
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Идентификатор Офиса
     */
    private Long officeId;

    /**
     * имя работника
     */
    private String firstName;

    /**
     * среднее, второе имя работника
     */
    private String middleName;

    /**
     * фамилия работника
     */
    private String lastName;

    /**
     * телефон
     */
    private String phone;

    /**
     * номер документа
     */
    private String docNumber;

    /**
     * дата документа
     */
    private LocalDate docDate;

    /**
     * Код-документа
     */
    @JsonProperty("docCode")
    private Long docCodeNum;

    /**
     * тип документа
     */
    private String docName;

    /**
     * код гражданства
     */
    private Long citizenshipCode;

    /**
     * наименование гражданства
     */
    private String citizenshipName;

    /**
     * наименование должности
     */
    @JsonProperty("position")
    private String positionName;

    /**
     * Статус работника: true - идентифицируется, false - не идентифицируется
     */
    private Boolean isIdentified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }


    public Long getDocCodeNum() {
        return docCodeNum;
    }

    public void setDocCodeNum(Long docCodeNum) {
        this.docCodeNum = docCodeNum;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }
}
