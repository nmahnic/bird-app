package data

import domain.model.BirdImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BirdsRemoteSource(
    private val httpClient: HttpClient,
    private val mapper: BirdImageMapper
) {

    suspend fun getImages(): List<BirdImage> {
        val imageEntityList = httpClient
            .get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<BirdImageEntity>>()
        return imageEntityList.map{ mapper.toModel(it) }
    }
}