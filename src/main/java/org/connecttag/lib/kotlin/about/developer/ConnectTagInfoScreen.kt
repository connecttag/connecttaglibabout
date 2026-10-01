package org.connecttag.lib.kotlin.about.developer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Pre-configured About Developer screen for ConnectTag.
 * Uses ConnectTag developer profile defaults, social networks, repositories, and apps.
 */
@Composable
fun ConnectTagInfoScreen(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    footerContent: (@Composable () -> Unit)? = null,
) {
    val profile = ConnectTagDeveloperDefaults.createProfile()
    AboutDeveloperScreen(
        profile = profile,
        onBack = onBack,
        modifier = modifier,
        onUrlClick = onUrlClick,
        footerContent = footerContent
    )
}
