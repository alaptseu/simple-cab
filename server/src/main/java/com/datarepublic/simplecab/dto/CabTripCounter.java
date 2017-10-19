package com.datarepublic.simplecab.dto;

/**
 * @author Alex L.
 */
public class CabTripCounter {

    private String medallion;

    private int trips;

    public CabTripCounter(String medallion, int trips) {
        this.medallion = medallion;
        this.trips = trips;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public String getMedallion() {
        return medallion;
    }

    public int getTrips() {
        return trips;
    }


}
