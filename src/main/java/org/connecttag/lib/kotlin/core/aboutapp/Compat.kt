package org.connecttag.lib.kotlin.core.aboutapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.connecttag.lib.kotlin.about.*
import org.connecttag.lib.kotlin.about.compose.*

// Typealiases for backwards compatibility
typealias AboutAppAction = org.connecttag.lib.kotlin.about.AboutAppAction
typealias AboutAppActionDispatcher = org.connecttag.lib.kotlin.about.AboutAppActionDispatcher
typealias AboutAppProfile = org.connecttag.lib.kotlin.about.AboutAppProfile
typealias AppStoreSpec = org.connecttag.lib.kotlin.about.AppStoreSpec
typealias AboutAppSocialLink = org.connecttag.lib.kotlin.about.AboutAppSocialLink
typealias AboutData = org.connecttag.lib.kotlin.about.AboutData
typealias AboutAppDialogLabels = org.connecttag.lib.kotlin.about.AboutAppDialogLabels
typealias AboutAppWebsite = org.connecttag.lib.kotlin.about.AboutAppWebsite
typealias AboutAppRepository = org.connecttag.lib.kotlin.about.AboutAppRepository
typealias AboutAppPresentationMode = org.connecttag.lib.kotlin.about.AboutAppPresentationMode
typealias AboutAppPresentationOptions = org.connecttag.lib.kotlin.about.AboutAppPresentationOptions
typealias AboutAppUiProperties = org.connecttag.lib.kotlin.about.AboutAppUiProperties
typealias SocialLinkPolicySource = org.connecttag.lib.kotlin.about.SocialLinkPolicySource
typealias AllowAllSocialLinkPolicySource = org.connecttag.lib.kotlin.about.AllowAllSocialLinkPolicySource
typealias SocialLinkResolver = org.connecttag.lib.kotlin.about.SocialLinkResolver
typealias SocialLinkKind = org.connecttag.lib.kotlin.about.SocialLinkKind
typealias SocialNetworkSpec = org.connecttag.lib.kotlin.about.SocialNetworkSpec
typealias SocialNetworkCatalog = org.connecttag.lib.kotlin.about.SocialNetworkCatalog
typealias SocialNetworkPlatform = org.connecttag.lib.kotlin.about.SocialNetworkPlatform
typealias AboutAppRowAction = org.connecttag.lib.kotlin.about.AboutAppRowAction
typealias AboutAppSocialLinkData = org.connecttag.lib.kotlin.about.AboutAppSocialLinkData
typealias AboutAppPresentationModeParser = org.connecttag.lib.kotlin.about.compose.AboutAppPresentationModeParser

@Composable
fun BaseAboutAppScreen(
    appName: String,
    versionText: String,
    description: String,
    appIcon: Any,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = androidx.compose.ui.res.stringResource(org.connecttag.lib.kotlin.about.R.string.about_app),
    packageName: String? = null,
    coverContent: @Composable () -> Unit = {},
    actions: List<org.connecttag.lib.kotlin.about.AboutAppRowAction> = emptyList(),
    websites: List<org.connecttag.lib.kotlin.about.AboutAppWebsite> = emptyList(),
    socialLinks: List<org.connecttag.lib.kotlin.about.AboutAppSocialLinkData> = emptyList(),
    developerAction: (() -> Unit)? = null,
    footerContent: (@Composable () -> Unit)? = null,
    onUrlClick: (String) -> Unit = {}
) {
    org.connecttag.lib.kotlin.about.BaseAboutAppScreen(
        appName = appName,
        versionText = versionText,
        description = description,
        appIcon = appIcon,
        onBack = onBack,
        modifier = modifier,
        title = title,
        packageName = packageName,
        coverContent = coverContent,
        actions = actions,
        websites = websites,
        socialLinks = socialLinks,
        developerAction = developerAction,
        footerContent = footerContent,
        onUrlClick = onUrlClick
    )
}

@Composable
fun ConnectTagInfoScreen(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit
) {
    org.connecttag.lib.kotlin.about.ConnectTagInfoScreen(
        onBack = onBack,
        onUrlClick = onUrlClick
    )
}
