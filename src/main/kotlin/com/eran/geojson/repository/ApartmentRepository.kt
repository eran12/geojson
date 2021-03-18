package com.eran.geojson.repository

import com.eran.geojson.beans.Apartment
import javax.transaction.Transactional
import javax.websocket.server.PathParam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * @author Eran Eichenbaum - 18/03/2021.
 */
@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface ApartmentRepository : JpaRepository<Apartment, Long> {

    @Query("SELECT a FROM Apartment AS a WHERE a.price >= :minPrice AND a.price <= :maxPrice AND a.bedrooms >= :minBedrooms AND a.bedrooms <= :maxBedrooms AND a.bathrooms >= :minBathrooms AND a.bathrooms <= :maxBathrooms")
    fun findByRang(
        @PathParam("min_price") minPrice: Int,
        @PathParam("max_price") maxPrice: Int,
        @PathParam("min_bed") minBedrooms: Int,
        @PathParam("max_bed") maxBedrooms: Int,
        @PathParam("min_bath") minBathrooms: Int,
        @PathParam("max_bath") maxBathrooms: Int,
    ): List<Apartment>

}
