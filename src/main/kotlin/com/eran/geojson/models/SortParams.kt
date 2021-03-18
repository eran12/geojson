package com.eran.geojson.models

/**
 * @author Eran Eichenbaum - 18/03/2021.
 */
data class SortParams(
    val minPrice: Int = 0,
    val maxPrice: Int = Int.MAX_VALUE,
    val minBed: Int = 0,
    val maxBed: Int = Int.MAX_VALUE,
    val minBath: Int = 0,
    val maxBath: Int = Int.MAX_VALUE,
)
