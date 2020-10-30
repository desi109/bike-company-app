package com.dmilusheva.bikecompany.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
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
    @Id       //Hibernate will create an id for us.
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;


    private String name;
    private String email;
    private String phone;
    private String model;
    private String serialNumber;


    private BigDecimal purchasePrice;
    /**
     * @JsonIgnoreProperties -> ignore properties, which Hibernate adds automatically to our objects
     * @JsonFormat -> allows customization
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date purchaseDate;
    private boolean contact;

    public Bike() {
    }

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

    @JsonProperty
    public Long getId() {
        return id; //only allow user to get not set
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
}
