package com.eran.geojson.extensions

import com.eran.geojson.beans.Apartment
import com.eran.geojson.models.GeoResponse
import com.eran.geojson.models.Geometry
import com.eran.geojson.models.Properties

/**
 * @author Eran Eichenbaum - 19/03/2021.
 */
fun List<Apartment>.toGeoResponse(): Set<GeoResponse> {
    return this.map {
        GeoResponse(
            geometry = Geometry(coordinates = listOf(it.lat, it.lng)),
            Properties(
                id = it.id,
                street = it.street,
                status = it.status,
                price = it.price,
                bedrooms = it.bedrooms,
                bathrooms = it.bathrooms,
                sqFt = it.sqFt
            )
        )
    }.toSet()
}
