package org.connecttag.lib.kotlin.about

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.connecttag.lib.kotlin.about.app.AboutAppScreen
import org.connecttag.lib.kotlin.about.app.AboutAppRowAction as AppRowAction
import org.connecttag.lib.kotlin.about.app.AboutAppSocialLinkData as AppSocialLinkData
import org.connecttag.lib.kotlin.about.app.AboutAppWebsite as AppWebsite
import org.connecttag.lib.kotlin.about.common.resolveSocialIconResource

/**
 * Backwards-compatible delegate for [BaseAboutAppScreen].
 * Delegates rendering to the dedicated [org.connecttag.lib.kotlin.about.app.AboutAppScreen].
 */
@Composable
fun BaseAboutAppScreen(
    appName: String,
    versionText: String,
    description: String,
    appIcon: Any,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.about_app),
    packageName: String? = null,
    coverContent: @Composable () -> Unit = {},
    actions: List<AboutAppRowAction> = emptyList(),
    websites: List<AboutAppWebsite> = emptyList(),
    socialLinks: List<AboutAppSocialLinkData> = emptyList(),
    developerAction: (() -> Unit)? = null,
    footerContent: (@Composable () -> Unit)? = null,
    onUrlClick: (String) -> Unit = {}
) {
    AboutAppScreen(
        appName = appName,
        versionText = versionText,
        description = description,
        appIcon = appIcon,
        onBack = onBack,
        modifier = modifier,
        title = title,
        packageName = packageName,
        coverContent = coverContent,
        actions = actions.map { AppRowAction(it.title, it.icon, it.url, it.onClick) },
        websites = websites.map { AppWebsite(it.label, it.url) },
        socialLinks = socialLinks.map { AppSocialLinkData(it.iconRes, it.url, it.tint) },
        onOpenDeveloperInfo = developerAction,
        footerContent = footerContent,
        onUrlClick = onUrlClick
    )
}

/**
 * Overload that uses [AboutAppProfile] for backward compatibility.
 */
@Composable
fun BaseAboutAppScreen(
    profile: AboutAppProfile,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    versionText: String = "",
    appIcon: Any = R.drawable.connect_tag_logo,
    onUrlClick: (String) -> Unit = {},
    developerAction: (() -> Unit)? = null,
    extraActions: List<AboutAppRowAction> = emptyList(),
    translate: (String?) -> String = { it ?: "" }
) {
    BaseAboutAppScreen(
        appName = profile.name ?: translate(profile.nameKey),
        versionText = versionText,
        description = profile.description ?: translate(profile.descriptionKey),
        appIcon = profile.logo ?: appIcon,
        onBack = onBack,
        modifier = modifier,
        websites = profile.stores.map { AboutAppWebsite(it.label ?: it.type, it.url) } +
            profile.socialLinks
                .filter { it.network.kind == SocialLinkKind.Url && it.active }
                .map { AboutAppWebsite(it.label ?: it.network.displayName, it.network.webUrlPrefix + it.value) },
        socialLinks = profile.socialLinks.filter { it.active }.mapNotNull { link ->
            resolveSocialIconResource(link.network.iconKey)?.let { iconRes ->
                AboutAppSocialLinkData(
                    iconRes = iconRes,
                    url = link.network.webUrlPrefix + link.value
                )
            }
        },
        actions = buildList {
            addAll(extraActions)
            profile.privacyPolicyUrl?.let { url ->
                add(AboutAppRowAction(translate("privacy_policy").ifBlank { "Privacy Policy" }, Icons.Default.PrivacyTip, url))
            }
            profile.termsOfUseUrl?.let { url ->
                add(AboutAppRowAction(translate("terms_of_use").ifBlank { "Terms of Service" }, Icons.Default.Description, url))
            }
        },
        developerAction = developerAction,
        onUrlClick = onUrlClick
    )
}

data class AboutAppRowAction(
    val title: String,
    val icon: Any, // ImageVector, Painter, or Int
    val url: String? = null,
    val onClick: (() -> Unit)? = null
) {
    constructor(title: String, icon: Any, url: String) : this(title = title, icon = icon, url = url, onClick = null)
    constructor(title: String, icon: Any, onClick: () -> Unit) : this(title = title, icon = icon, url = null, onClick = onClick)
}

data class AboutAppSocialLinkData(
    val iconRes: Int,
    val url: String,
    val tint: androidx.compose.ui.graphics.Color? = null
)
