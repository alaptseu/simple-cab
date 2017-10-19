package com.datarepublic.simplecab.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Alex L.
 */
@Entity
@Table(name = "cab_trip_data")
public class CabTripData implements Serializable {

    @Id
    @GeneratedValue
    private String medallion;

    private String hackLicense;

    private String vendorId;

    private Integer rateCode;

    private String storeAndFwdFlag;

    private Date pickupDatetime;

    private Date dropoffDatetime;

    private Integer passengerCount;

    private Integer tripTimeInSecs;

    private Double tripDistance;

    private Double pickupLongitude;

    private Double pickupLatitude;

    private Double dropoff_longitude;

    private Double dropoff_latitude;

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    @Column(name = "hack_license")
    public String getHackLicense() {
        return hackLicense;
    }

    public void setHackLicense(String hackLicense) {
        this.hackLicense = hackLicense;
    }

    @Column(name = "vendor_id")
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Column(name = "rate_code")
    public Integer getRateCode() {
        return rateCode;
    }

    public void setRateCode(Integer rateCode) {
        this.rateCode = rateCode;
    }

    @Column(name = "store_and_fwd_flag")
    public String getStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    public void setStoreAndFwdFlag(String storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
    }

    @Column(name = "pickup_datetime")
    public Date getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(Date pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    @Column(name = "passenger_count")
    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Column(name = "dropoff_datetime")
    public Date getDropoffDatetime() {
        return dropoffDatetime;
    }

    public void setDropoffDatetime(Date dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    @Column(name = "trip_time_in_secs")
    public Integer getTripTimeInSecs() {
        return tripTimeInSecs;
    }

    public void setTripTimeInSecs(Integer tripTimeInSecs) {
        this.tripTimeInSecs = tripTimeInSecs;
    }

    @Column(name = "trip_distance")
    public Double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Column(name = "pickup_longitude")
    public Double getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(Double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    @Column(name = "pickup_latitude")
    public Double getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(Double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public Double getDropoff_longitude() {
        return dropoff_longitude;
    }

    public void setDropoff_longitude(Double dropoff_longitude) {
        this.dropoff_longitude = dropoff_longitude;
    }

    public Double getDropoff_latitude() {
        return dropoff_latitude;
    }

    public void setDropoff_latitude(Double dropoff_latitude) {
        this.dropoff_latitude = dropoff_latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CabTripData)) {
            return false;
        }
        CabTripData data = (CabTripData) o;
        return new EqualsBuilder()
            .appendSuper(super.equals(data))
            .append(getMedallion(), data.getMedallion())
            .append(getPickupDatetime(), data.getPickupDatetime())
            .append(getHackLicense(), data.getHackLicense())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
            append(getMedallion()).
            append(getPickupDatetime()).
            append(getHackLicense()).
            toHashCode();
    }

}
