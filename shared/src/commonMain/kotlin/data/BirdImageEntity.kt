package data

import kotlinx.serialization.Serializable

@Serializable
data class BirdImageEntity(
    val author: String,
    val category: String,
    val path: String
)