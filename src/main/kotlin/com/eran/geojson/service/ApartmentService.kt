package com.eran.geojson.service

import com.eran.geojson.beans.Apartment
import com.eran.geojson.beans.ApartmentStatus
import com.eran.geojson.extensions.toGeoResponse
import com.eran.geojson.models.ApartmentFileModel
import com.eran.geojson.models.GeoResponse
import com.eran.geojson.models.SortParams
import com.eran.geojson.repository.ApartmentRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import javax.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * @author Eran Eichenbaum - 18/03/2021.
 */
@Service
class ApartmentService(private val apartmentRepository: ApartmentRepository) {

    init {
        this.readFileAndInsertToDb()
    }

    @Transactional
    fun getAll(sortParams: SortParams): ResponseEntity<Set<GeoResponse>> {
        if (sortParams != SortParams()) {
            return ResponseEntity.ok(
                apartmentRepository.findByRang(
                    minPrice = sortParams.minPrice,
                    maxPrice = sortParams.maxPrice,
                    minBedrooms = sortParams.minBed,
                    maxBedrooms = sortParams.maxBed,
                    minBathrooms = sortParams.minBath,
                    maxBathrooms = sortParams.maxBath
                ).toGeoResponse()
            )
        }
        return ResponseEntity.ok(apartmentRepository.findAll().toGeoResponse())
    }

    @Transactional
    fun readFileAndInsertToDb(fileName: String = "listing-details.csv") {
        val apartments = csvReader().open(javaClass.classLoader.getResource(fileName).file) {
            var apartments = emptySet<Apartment>()
            readAllWithHeaderAsSequence().forEach {
                apartments = apartments.plus(
                    Apartment(
                        id = 0,
                        street = it.getOrDefault(ApartmentFileModel.STREET.name.toLowerCase(), ""),
                        status = ApartmentStatus.valueOf(
                            it[ApartmentFileModel.STATUS.name.toLowerCase()]?.toUpperCase()
                                ?: ApartmentStatus.UNKNOWN.name
                        ),
                        price = (it.getOrDefault(ApartmentFileModel.PRICE.name.toLowerCase(), 0) as String).toInt(),
                        bedrooms = (it.getOrDefault(
                            ApartmentFileModel.BEDROOMS.name.toLowerCase(),
                            0
                        ) as String).toInt(),
                        bathrooms = (it.getOrDefault(
                            ApartmentFileModel.BATHROOMS.name.toLowerCase(),
                            0
                        ) as String).toInt(),
                        sqFt = (it.getOrDefault(ApartmentFileModel.SQ_FT.name.toLowerCase(), 0) as String).toInt(),
                        lat = (it.getOrDefault(ApartmentFileModel.LAT.name.toLowerCase(), 0F) as String).toFloat(),
                        lng = (it.getOrDefault(ApartmentFileModel.LNG.name.toLowerCase(), 0F) as String).toFloat(),
                    )
                )
            }
            return@open apartments
        }
        if (apartments.isNotEmpty()) {
            apartmentRepository.saveAll(apartments)
        }
    }
}
