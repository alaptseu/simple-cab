package com.datarepublic.simplecab.repository;

import com.datarepublic.simplecab.entity.CabTripData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public interface SimpleCabRepository extends JpaRepository<CabTripData, String> {

    Integer getCountByMedallionAndPickupDatetime(String medallion, Date pickupDatetime);

    @Query(value = "select * from cab_trip_data\n" +
        "where medallion in (?1)\n" +
        "  and DATE(pickup_datetime) = DATE(?2)", nativeQuery = true)
    Set<CabTripData> findBypickupDatetimeAndMedallions(Collection<String> medallion, Date pickupDatetime);

    /**
     *  spring data jpa supports direct mapping to pojo objects only from 2.0
     */
    @Query(value = "select medallion, count(medallion) AS trips from cab_trip_data\n" +
        "where medallion in (?1)\n" +
        "  and DATE(pickup_datetime) = DATE(?2)\n" +
        "GROUP BY medallion", nativeQuery = true)
    Set<Object[]> countByMedallionAndPickupDatetime(Collection<String> medallion, Date pickupDatetime);
}
