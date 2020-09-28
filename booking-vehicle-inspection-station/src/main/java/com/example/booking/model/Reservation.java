package com.example.booking.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String licenseOwner;

    @NotNull
    @Column(unique = true)
    private Long licenseNumber;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inspection_station_id")
    private InspectionStation inspectionStation;

    @NotNull
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseOwner() {
        return licenseOwner;
    }

    public void setLicenseOwner(String licenseOwner) {
        this.licenseOwner = licenseOwner;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public InspectionStation getInspectionStation() {
        return inspectionStation;
    }

    public void setInspectionStation(InspectionStation inspectionStation) {
        this.inspectionStation = inspectionStation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
