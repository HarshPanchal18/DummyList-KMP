package presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.entity.ProductEntity
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.theme.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItemScreen(product: ProductEntity, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        backgroundColor = Color.Background
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ProductThumbnail(product)
            ProductRating(product.rating)
            ProductBrandName(product.brand ?: "Nothing")
            ProductName(product.title ?: "Nothing")
            ProductPrice(product.price.toString())
            ProductDescription(product.description ?: "")
            ProductImages(product.images)
        }
    }
}

@Composable
fun ProductThumbnail(product: ProductEntity) {
    KamelImage(
        resource = asyncPainterResource(product.thumbnail ?: ""),
        contentDescription = "${product.category} by ${product.brand}",
        contentScale = ContentScale.Fit,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.0F)
    )
}

@Composable
fun ProductBrandName(brand: String) {
    Text(
        text = brand,
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.lightGray
    )
}

@Composable
fun ProductName(name: String) {
    Text(
        text = name,
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.mainText
    )
}

@Composable
fun ProductPrice(title: String) {
    Text(
        text = "$title $",
        modifier = Modifier.padding(2.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.red
    )
}

@Composable
fun ProductRating(rate: Float) {
    Row(modifier = Modifier.height(30.dp).padding(2.dp)) {
        for (i in 1..5) {
            if (i <= rate) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled star",
                    tint = Color.primary
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty star",
                    tint = Color.primary
                )
            }
        }
    }
}

@Composable
fun ProductDescription(description: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun ProductImages(images: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
    ) {
        items(images.size) { i ->
            KamelImage(
                resource = asyncPainterResource(images[i]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.0F)
            )
        }
    }
}
