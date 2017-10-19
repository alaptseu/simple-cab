package com.datarepublic.simplecab.controller;

import com.datarepublic.simplecab.dto.CabTripCounter;
import com.datarepublic.simplecab.entity.CabTripData;
import com.datarepublic.simplecab.service.CabTripDataServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;

import static org.apache.commons.lang.BooleanUtils.toBooleanDefaultIfNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Alex L.
 */
@RestController
@RequestMapping(value = "/cabtrip", produces = APPLICATION_JSON_VALUE)
public class CabTripController {

    private final CabTripDataServiceImpl cabService;

    public CabTripController(CabTripDataServiceImpl cabService) {
        this.cabService = cabService;
    }

    @GetMapping
    public ResponseEntity<Set<CabTripCounter>> getCabDataCount(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date pickupDatetime,
                                                               @RequestParam(value = "medallion") List<String> medallions,
                                                               @RequestParam(value = "ignoreCache", required = false) Boolean ignoreCache) {
        if (!toBooleanDefaultIfNull(ignoreCache, false)) {
            return ok(cabService.getMedallionsSummary(pickupDatetime, medallions));
        } else {
            return ok(cabService.loadMedallionsSummary(pickupDatetime, medallions));
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCache() {
        cabService.evictCache();
        return ok(new Gson().toJson("Done"));
    }


}
