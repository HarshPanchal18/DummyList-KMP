package presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultScreen() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Hello, ${getPlatformName()}")
        Image(
            painter = painterResource("compose-multiplatform.xml"),
            contentDescription = null
        )
    }
}
