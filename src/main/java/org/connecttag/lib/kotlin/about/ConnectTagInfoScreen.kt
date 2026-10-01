package org.connecttag.lib.kotlin.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Backwards compatibility delegate for [ConnectTagInfoScreen].
 * Delegates to the dedicated [org.connecttag.lib.kotlin.about.developer.ConnectTagInfoScreen].
 */
@Composable
fun ConnectTagInfoScreen(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    footerContent: (@Composable () -> Unit)? = null,
) {
    org.connecttag.lib.kotlin.about.developer.ConnectTagInfoScreen(
        onBack = onBack,
        onUrlClick = onUrlClick,
        modifier = modifier,
        footerContent = footerContent
    )
}
