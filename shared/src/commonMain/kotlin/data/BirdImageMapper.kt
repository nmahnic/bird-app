package data

import domain.model.BirdImage

class BirdImageMapper {

    fun toModel(imageEntity: BirdImageEntity) = BirdImage(
        path = imageEntity.path,
        contentDescription = "${imageEntity.category} by ${imageEntity.author}"
    )
}