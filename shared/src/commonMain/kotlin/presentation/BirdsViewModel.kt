package presentation

import data.BirdImageMapper
import data.BirdsRemoteSource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.BirdImage
import httpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BirdsUiState(
    val images: List<BirdImage> = emptyList(),
)

class BirdsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BirdsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateImages()
    }

    private fun updateImages() {
        viewModelScope.launch{
            val images = BirdsRemoteSource(
                httpClient,
                BirdImageMapper()
            ).getImages()
            _uiState.update {
                it.copy(images = images)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }
}