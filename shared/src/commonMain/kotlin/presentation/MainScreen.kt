package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.model.BirdImage
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun BirdAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Color.Black
        ),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun MainScreen(viewModel: BirdsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for(category in uiState.categories) {
                Button(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(5.dp),
                    onClick = { viewModel.selectCategory(category) }
                ) {
                    Text(category)
                }
            }
        }
        AnimatedVisibility(uiState.selectedImages.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                content = {
                    items(uiState.selectedImages) {
                        BirdImageCell(it)
                    }
                }
            )
        }
    }
}

@Composable
fun BirdImageCell(image: BirdImage){
    val baseUrl = "https://sebi.io/demo-image-api/"
    KamelImage(
        asyncPainterResource(baseUrl + image.path),
        contentDescription =  image.contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
    )
}