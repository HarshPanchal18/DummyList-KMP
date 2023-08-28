package presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import presentation.theme.Color

@Composable
fun ErrorScreen(message: String) {

    var rotationAngle by remember { mutableStateOf(0F) }
    val rotation = remember { Animatable(0F) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = LinearEasing
                )
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(modifier = Modifier.size(48.dp),
                onDraw = {
                    val strokeWidth = size.width / 16F
                    drawCircle(
                        color = Color.Danger,
                        radius = size.minDimension / 2 - strokeWidth / 2,
                        center = Offset(x = size.width / 2, y = size.height / 2),
                        style = Stroke(width = strokeWidth)
                    )
                    drawArc(
                        color = Color.secondary,
                        startAngle = rotation.value.toDouble().toFloat() - 90,
                        sweepAngle = 60F,
                        useCenter = false,
                        style = Stroke(width = strokeWidth),
                        topLeft = Offset(strokeWidth, strokeWidth),
                        size = Size(
                            width = size.width - strokeWidth * 2,
                            height = size.height - strokeWidth * 2
                        )
                    )
                }
            )
            Text(
                text = "Error",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colors.error
            )
            Text(
                text = message,
                style = MaterialTheme.typography.body1,
                color = Color.Background,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
