package com.dmilusheva.bikecompany.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @JsonIgnoreProperties -> ignore hibernateLazyInitializer and handler, which Hibernate adds automatically to our objects.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bike {

    /**
     * As an entity for the database is created, there should be create a primary key for the object Bike.
     * @Id -> So the next attribute, represents the primary key id for the class Bike (it has Long data type !).
     *
     * @GeneratedValue -> tells that this is the id field for the entity and we want to generate it,
     * using the GenerationType.AUTO.
     *
     * Both annotations are used by JPA.
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;


    private String name;
    private String email;
    private String phone;
    private String model;
    private String serialNumber;

    /**
     * @JsonIgnoreProperties -> ignore properties, which Hibernate adds automatically to our objects
     * @JsonFormat -> allows customization
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private BigDecimal purchasePrice;
    private Date purchaseDate;
    private boolean contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
