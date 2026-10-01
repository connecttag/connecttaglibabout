package org.connecttag.lib.kotlin.about.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import org.connecttag.lib.kotlin.about.common.SocialNetworkPlatform
import org.connecttag.lib.kotlin.about.common.SocialNetworkSpec

/**
 * Primary model representing the Application profile and metadata.
 */
@Serializable
data class AboutAppInfo(
    val appName: String,
    val versionName: String = "",
    val versionCode: Long = 0,
    val packageName: String? = null,
    val description: String? = null,
    val logo: String? = null,
    val cover: String? = null,
    val websites: List<AboutAppWebsite> = emptyList(),
    val socialLinks: List<AboutAppSocialLink> = emptyList(),
    val privacyPolicyUrl: String? = null,
    val termsOfUseUrl: String? = null,
    val metadata: Map<String, String> = emptyMap(),
)

@Serializable
data class AboutAppWebsite(
    val label: String,
    val url: String,
)

@Serializable
data class AboutAppRepository(
    val label: String,
    val url: String,
)

@Serializable
data class AboutAppSocialLink(
    val network: SocialNetworkSpec,
    val value: String,
    val active: Boolean = true,
    val label: String? = null,
)

data class AboutAppRowAction(
    val title: String,
    val icon: Any, // ImageVector, Painter, or Int (DrawableRes ID)
    val url: String? = null,
    val onClick: (() -> Unit)? = null
) {
    constructor(title: String, icon: Any, url: String) : this(title = title, icon = icon, url = url, onClick = null)
    constructor(title: String, icon: Any, onClick: () -> Unit) : this(title = title, icon = icon, url = null, onClick = onClick)
}

data class AboutAppSocialLinkData(
    val iconRes: Int,
    val url: String,
    val tint: Color? = null
)

sealed interface AboutAppAction {
    data class OpenUrl(val url: String) : AboutAppAction
    data class SendEmail(val email: String) : AboutAppAction
    data class OpenPlayStore(val packageName: String) : AboutAppAction
}

fun interface AboutAppActionDispatcher {
    fun dispatch(action: AboutAppAction)
}

data class AboutAppDialogLabels(
    val infoContentDescription: String,
    val closeContentDescription: String,
    val websiteTitle: String,
)

enum class AboutAppPresentationMode {
    Dialog,
    BottomSheet,
    FullScreen,
}

data class AboutAppPresentationOptions(
    val mode: AboutAppPresentationMode = AboutAppPresentationMode.Dialog,
    val showCloseButton: Boolean = true,
    val dismissOnBack: Boolean = true,
    val maxDialogWidthDp: Int = 420,
    val showDragHandle: Boolean = true,
    val socialSpacingDp: Int = 8,
)

data class AboutAppUiProperties(
    val title: String,
    val labels: AboutAppDialogLabels,
    val name: String,
    val description: String? = null,
    val versionText: String? = null,
    val websites: List<AboutAppWebsite> = emptyList(),
    val repositories: List<AboutAppRepository> = emptyList(),
    val socialLinks: List<AboutAppSocialLink> = emptyList(),
    val metadata: Map<String, String> = emptyMap(),
    val options: AboutAppPresentationOptions = AboutAppPresentationOptions(),
    val translate: (String) -> String = { it },
    val logoContentDescription: String? = name.takeIf { it.isNotBlank() },
    val onWebsiteClick: (String) -> Unit = {},
    val onSocialLinkClick: (AboutAppSocialLink) -> Unit = {},
    val onSocialLinkLongClick: (AboutAppSocialLink) -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val logo: @Composable (contentDescription: String?) -> Unit = {},
    val cover: @Composable () -> Unit = {},
    val socialIcon: @Composable (AboutAppSocialLink) -> Unit = {},
)
