package com.example.booking.dto;


import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;

    private String licenseOwner;

    private Long licenseNumber;

    private LocalDateTime dateTime;

    private Long inspectionStationId;

    private Long customerId;

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

    public Long getInspectionStationId() {
        return inspectionStationId;
    }

    public void setInspectionStationId(Long inspectionStationId) {
        this.inspectionStationId = inspectionStationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
