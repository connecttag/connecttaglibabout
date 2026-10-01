package org.connecttag.lib.kotlin.about.developer

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import org.connecttag.lib.kotlin.about.R

/**
 * Model representing any developer or organization profile.
 */
@Serializable
data class DeveloperProfile(
    val id: String = "developer",
    val name: String,
    val description: String? = null,
    val logoUrl: String? = null,
    val logoRes: Int? = null,
    val website: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val repositories: List<DeveloperRepository> = emptyList(),
    val socialLinks: List<DeveloperSocialLink> = emptyList(),
    val apps: List<DeveloperAppItem> = emptyList(),
    val metadata: Map<String, String> = emptyMap(),
)

@Serializable
data class DeveloperRepository(
    val title: String,
    val url: String,
    val iconRes: Int? = null,
    val iconKey: String? = null,
)

@Serializable
data class DeveloperSocialLink(
    val title: String = "",
    val url: String,
    val iconRes: Int? = null,
    val iconKey: String? = null,
)

@Serializable
data class DeveloperAppItem(
    val title: String,
    val url: String,
    val iconRes: Int? = null,
    val iconKey: String? = null,
    val description: String? = null,
)

/**
 * Built-in defaults for ConnectTag Developer profile and links.
 */
object ConnectTagDeveloperDefaults {
    const val WEBSITE_URL = "https://connecttag.org"
    const val EMAIL = "info@connecttag.org"
    const val GITHUB_URL = "https://github.com/connecttagye"
    const val GITLAB_URL = "https://gitlab.com/connecttagye"
    const val APPS_WEBSITE_URL = "https://connecttag.org/company/projects/apps/"
    const val GOOGLE_PLAY_DEV_URL = "https://play.google.com/store/apps/dev?id=6507822077747485835"
    const val TELEGRAM_APPS_URL = "https://t.me/ConnectTagApps"

    @Composable
    fun createProfile(): DeveloperProfile {
        return DeveloperProfile(
            id = "connecttag",
            name = stringResource(R.string.developer_name_full),
            description = stringResource(R.string.developer_description_full),
            logoRes = R.drawable.connect_tag_logo,
            website = WEBSITE_URL,
            email = EMAIL,
            repositories = defaultRepositories(),
            socialLinks = defaultSocialLinks(),
            apps = defaultApps()
        )
    }

    fun defaultRepositories(): List<DeveloperRepository> = listOf(
        DeveloperRepository(
            title = "GitHub",
            url = GITHUB_URL,
            iconRes = R.drawable.github,
            iconKey = "github"
        ),
        DeveloperRepository(
            title = "GitLab",
            url = GITLAB_URL,
            iconRes = R.drawable.gitlab,
            iconKey = "gitlab"
        ),
    )

    fun defaultSocialLinks(): List<DeveloperSocialLink> = listOf(
        DeveloperSocialLink("Facebook", "https://www.facebook.com/connecttagye", R.drawable.facebook, "facebook"),
        DeveloperSocialLink("Telegram", "https://t.me/connecttagye", R.drawable.telegram, "telegram"),
        DeveloperSocialLink("WhatsApp", "https://whatsapp.com/channel/0029VaasCef8qIztDeS8Sp1R", R.drawable.whatsapp, "whatsapp"),
        DeveloperSocialLink("X", "https://x.com/connecttagye", R.drawable.x, "x"),
        DeveloperSocialLink("LinkedIn", "https://www.linkedin.com/in/connecttagye", R.drawable.linkedin, "linkedin"),
        DeveloperSocialLink("Instagram", "https://www.instagram.com/connecttagye", R.drawable.instagram, "instagram"),
        DeveloperSocialLink("Threads", "https://www.threads.net/@connecttagye", R.drawable.threads, "threads"),
        DeveloperSocialLink("Snapchat", "https://www.snapchat.com/add/connecttagye", R.drawable.snapchat, "snapchat"),
        DeveloperSocialLink("TikTok", "https://www.tiktok.com/@connecttagye", R.drawable.tiktok, "tiktok"),
        DeveloperSocialLink("YouTube", "https://www.youtube.com/@connecttagye", R.drawable.youtube, "youtube"),
        DeveloperSocialLink("Kik", "http://kik.me/connecttagye", R.drawable.kik, "kik"),
        DeveloperSocialLink("Pinterest", "https://www.pinterest.com/connecttagye", R.drawable.pinterest, "pinterest"),
        DeveloperSocialLink("Reddit", "https://www.reddit.com/user/connecttagye", R.drawable.reddit, "reddit"),
    )

    @Composable
    fun defaultApps(): List<DeveloperAppItem> = listOf(
        DeveloperAppItem(
            title = stringResource(R.string.browse_our_apps_website),
            url = APPS_WEBSITE_URL,
            iconRes = R.drawable.website,
            iconKey = "website"
        ),
        DeveloperAppItem(
            title = "Google Play",
            url = GOOGLE_PLAY_DEV_URL,
            iconRes = R.drawable.google_play,
            iconKey = "googleplay"
        ),
        DeveloperAppItem(
            title = "Telegram",
            url = TELEGRAM_APPS_URL,
            iconRes = R.drawable.telegram,
            iconKey = "telegram"
        ),
    )
}
