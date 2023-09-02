package data

import domain.model.BirdImage

class BirdImageMapper {

    fun toModel(imageEntity: BirdImageEntity) = BirdImage(
        path = imageEntity.path,
        category = imageEntity.category,
        contentDescription = "${imageEntity.category} by ${imageEntity.author}"
    )
}