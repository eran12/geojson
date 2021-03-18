package com.eran.geojson.beans

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Eran Eichenbaum - 18/03/2021.
 */
@Entity
class Apartment(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val street: String = "",
    val status: ApartmentStatus = ApartmentStatus.UNKNOWN,
    val price: Int = 0,
    val bedrooms: Int = 0,
    val bathrooms: Int = 0,
    val sqFt: Int = 0,
    val lat: Float = 0F,
    val lng: Float = 0F
)

enum class ApartmentStatus {
    PENDING,
    SOLD,
    ACTIVE,
    UNKNOWN
}
