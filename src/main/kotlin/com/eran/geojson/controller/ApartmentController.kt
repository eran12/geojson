package com.eran.geojson.controller

import com.eran.geojson.beans.Apartment
import com.eran.geojson.models.GeoResponse
import com.eran.geojson.models.SortParams
import com.eran.geojson.service.ApartmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Eran Eichenbaum - 18/03/2021.
 */
@RestController("/")
class ApartmentController(private val apartmentService: ApartmentService) {

    @GetMapping("/listing")
    fun getAll(
        @RequestParam(name = "min_price", required = false) minPrice: Int?,
        @RequestParam(name = "max_price", required = false) maxPrice: Int?,
        @RequestParam(name = "min_bed", required = false) minBedrooms: Int?,
        @RequestParam(name = "max_bed", required = false) maxBedrooms: Int?,
        @RequestParam(name = "min_bath", required = false) minBathrooms: Int?,
        @RequestParam(name = "max_bath", required = false) maxBathrooms: Int?,
    ): ResponseEntity<Set<GeoResponse>> {
        return apartmentService.getAll(
            SortParams(
                minPrice = minPrice ?: 0,
                maxPrice = maxPrice ?: Int.MAX_VALUE,
                minBath = minBathrooms ?: 0,
                minBed = minBedrooms ?: 0,
                maxBath = maxBathrooms ?: Int.MAX_VALUE,
                maxBed = maxBedrooms ?: Int.MAX_VALUE

            )
        )
    }
}
