package org.connecttag.lib.kotlin.about.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.Locale

/**
 * Interface for enums that have a specific value used in serialized forms.
 */
interface WireValueEnum {
    val wireValue: String
}

/**
 * A stable lowercase implementation that doesn't depend on the system locale.
 */
fun String.lowercaseStable(): String {
    return this.lowercase(Locale.ROOT)
}

/**
 * Utility for handling external URI policies.
 */
object ExternalUriPolicy {
    fun isSupportedAbsoluteUri(uri: String): Boolean {
        return uri.startsWith("http://", ignoreCase = true) ||
                uri.startsWith("https://", ignoreCase = true) ||
                uri.startsWith("fb://", ignoreCase = true) ||
                uri.startsWith("twitter://", ignoreCase = true) ||
                uri.startsWith("instagram://", ignoreCase = true) ||
                uri.startsWith("market://", ignoreCase = true) ||
                uri.startsWith("mailto:", ignoreCase = true)
    }
}

/**
 * Reusable icon container with tinted background styling.
 */
@Composable
fun AboutDuotoneIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    tint: Color = MaterialTheme.colorScheme.primary,
    containerSize: Dp = 38.dp,
    iconSize: Dp = 20.dp,
) {
    AboutDuotoneIconContainer(
        tint = tint,
        containerSize = containerSize,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize),
            tint = tint.takeOrElse { MaterialTheme.colorScheme.primary },
        )
    }
}

@Composable
fun AboutDuotoneIcon(
    painter: Painter,
    contentDescription: String?,
    tint: Color = MaterialTheme.colorScheme.primary,
    containerSize: Dp = 38.dp,
    iconSize: Dp = 20.dp,
) {
    AboutDuotoneIconContainer(
        tint = tint,
        containerSize = containerSize,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize),
            tint = tint.takeOrElse { MaterialTheme.colorScheme.primary },
        )
    }
}

@Composable
private fun AboutDuotoneIconContainer(
    tint: Color,
    containerSize: Dp,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        modifier = Modifier.size(containerSize),
        shape = CircleShape,
        color = tint.copy(alpha = 0.12f),
        contentColor = tint,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}
