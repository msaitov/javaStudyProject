package ru.msaitov.practice.model.employee;

import ru.msaitov.practice.model.Office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.time.LocalDate;

/**
 * Работник
 */
@Entity(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Long version;

    /**
     * Связь: много Работников к одному Офису
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * имя работника
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * среднее, второе имя работника
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * фамилия работника
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * телефон работника
     */
    @Column(name = "phone", length = 30, unique = true)
    private String phone;

    /**
     * номер документа работника
     */
    @Column(name = "doc_number", length = 50, unique = true)
    private String docNumber;

    /**
     * дата документа работника
     */
    @Column(name = "doc_date")
    private LocalDate docDate;

    /**
     * Связь: много Работников к одному Код-документа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code_id")
    private DocCode docCode;

    /**
     * Связь: много Работников к одному Гражданству
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private Citizenship citizenship;

    /**
     * Связь: много Работников к одной Должности
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    /**
     * Статус работника: true - идентифицируется, false - не идентифицируется
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;


    /**
     * Конструктор для hibernate
     */
    public Employee() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
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

    public DocCode getDocCode() {
        return docCode;
    }

    public void setDocCode(DocCode docCode) {
        this.docCode = docCode;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }
}
