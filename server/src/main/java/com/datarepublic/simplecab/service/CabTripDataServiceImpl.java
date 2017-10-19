package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.dto.CabTripCounter;
import com.datarepublic.simplecab.repository.SimpleCabRepository;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Alex L.
 */
@Service
@ParametersAreNonnullByDefault
public class CabTripDataServiceImpl {

    private final Logger logger = getLogger(getClass());

    private final SimpleCabRepository simpleCabRepository;

    public CabTripDataServiceImpl(SimpleCabRepository simpleCabRepository) {
        this.simpleCabRepository = simpleCabRepository;
    }

    @Cacheable(cacheNames="tripData")
    public Set<CabTripCounter> getMedallionsSummary(Date pickupDate, Collection<String> medallions){
       return findMedallionsSummary(pickupDate, medallions);
    }

    @CachePut(value="tripData")
    public Set<CabTripCounter> loadMedallionsSummary(Date pickupDate, Collection<String> medallions){
       return findMedallionsSummary(pickupDate, medallions);
    }

    private Set<CabTripCounter> findMedallionsSummary(Date pickupDate, Collection<String> medallions){
        Set<Object[]> rawData = simpleCabRepository.countByMedallionAndPickupDatetime(medallions, pickupDate);
        if (isEmpty(rawData)){
            logger.info("Nothing found");
            return null;
        }
        return rawData.stream().map(object -> new CabTripCounter(valueOf(object[0]), ((BigInteger) object[1]).intValue())).collect(toSet());
    }

    @CacheEvict(cacheNames="tripData", allEntries=true)
    public void evictCache(){
        logger.info("Cache evicting....");
    }

}
