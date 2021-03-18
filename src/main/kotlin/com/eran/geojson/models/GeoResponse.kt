package com.eran.geojson.models

import com.eran.geojson.beans.ApartmentStatus

/**
 * @author Eran Eichenbaum - 19/03/2021.
 */
data class GeoResponse(
    val geometry: Geometry,
    val properties: Properties
)

data class Geometry(
    val type: String = "Point",
    val coordinates: List<Float>
)

data class Properties(
    val id: Long,
    val street: String = "",
    val status: ApartmentStatus = ApartmentStatus.UNKNOWN,
    val price: Int = 0,
    val bedrooms: Int = 0,
    val bathrooms: Int = 0,
    val sqFt: Int = 0,
)
