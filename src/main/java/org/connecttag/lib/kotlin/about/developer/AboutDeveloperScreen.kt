package org.connecttag.lib.kotlin.about.developer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.connecttag.lib.kotlin.about.R
import org.connecttag.lib.kotlin.about.common.resolveSocialIconResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutDeveloperScreen(
    name: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    logo: Any? = null,
    website: String? = null,
    email: String? = null,
    phone: String? = null,
    repositories: List<DeveloperRepository> = emptyList(),
    socialLinks: List<DeveloperSocialLink> = emptyList(),
    apps: List<DeveloperAppItem> = emptyList(),
    onUrlClick: (String) -> Unit = {},
    footerContent: (@Composable () -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar / Logo
            if (logo != null) {
                Surface(
                    modifier = Modifier.size(100.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                ) {
                    DeveloperLogoImage(model = logo, modifier = Modifier.padding(16.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Developer Name
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Developer Description
            if (!description.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Website & Contact Info
            if (!website.isNullOrBlank()) {
                DeveloperInfoRow(
                    icon = Icons.Default.Language,
                    label = stringResource(R.string.developer_website),
                    value = website.removePrefix("https://").removePrefix("http://"),
                    onClick = { onUrlClick(website) }
                )
            }

            if (!email.isNullOrBlank()) {
                DeveloperInfoRow(
                    icon = Icons.Default.Email,
                    label = stringResource(R.string.developer_email),
                    value = email,
                    onClick = { onUrlClick("mailto:$email") }
                )
            }

            if (!phone.isNullOrBlank()) {
                DeveloperInfoRow(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = phone,
                    onClick = { onUrlClick("tel:$phone") }
                )
            }

            // Repositories Section
            if (repositories.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.code_repositories),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    items(repositories) { repo ->
                        val iconRes = repo.iconRes ?: resolveSocialIconResource(repo.iconKey) ?: R.drawable.github
                        DeveloperSocialBadge(
                            iconRes = iconRes,
                            label = repo.title,
                            onClick = { onUrlClick(repo.url) }
                        )
                    }
                }
            }

            // Social & Contact Section
            if (socialLinks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.contact_developer),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(socialLinks) { link ->
                        val iconRes = link.iconRes ?: resolveSocialIconResource(link.iconKey) ?: R.drawable.website
                        DeveloperSocialBadge(
                            iconRes = iconRes,
                            label = link.title,
                            onClick = { onUrlClick(link.url) }
                        )
                    }
                }
            }

            // Apps Section
            if (apps.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.Download_Our_Apps),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    items(apps) { appItem ->
                        val iconRes = appItem.iconRes ?: resolveSocialIconResource(appItem.iconKey) ?: R.drawable.website
                        DeveloperSocialBadge(
                            iconRes = iconRes,
                            label = appItem.title,
                            onClick = { onUrlClick(appItem.url) }
                        )
                    }
                }
            }

            // Footer
            if (footerContent != null) {
                Spacer(modifier = Modifier.height(32.dp))
                footerContent()
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/**
 * Convenience overload that takes [DeveloperProfile].
 */
@Composable
fun AboutDeveloperScreen(
    profile: DeveloperProfile,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onUrlClick: (String) -> Unit = {},
    footerContent: (@Composable () -> Unit)? = null,
) {
    AboutDeveloperScreen(
        name = profile.name,
        description = profile.description,
        logo = profile.logoRes ?: profile.logoUrl,
        website = profile.website,
        email = profile.email,
        phone = profile.phone,
        repositories = profile.repositories,
        socialLinks = profile.socialLinks,
        apps = profile.apps,
        onBack = onBack,
        modifier = modifier,
        onUrlClick = onUrlClick,
        footerContent = footerContent
    )
}

@Composable
private fun DeveloperLogoImage(model: Any, modifier: Modifier = Modifier) {
    when (model) {
        is Painter -> Image(painter = model, contentDescription = null, modifier = modifier)
        is ImageVector -> Image(imageVector = model, contentDescription = null, modifier = modifier)
        else -> AsyncImage(model = model, contentDescription = null, modifier = modifier)
    }
}

@Composable
private fun DeveloperInfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun DeveloperSocialBadge(
    iconRes: Int,
    label: String,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = tint.copy(alpha = 0.1f),
        border = BorderStroke(1.dp, tint.copy(alpha = 0.2f)),
        modifier = Modifier.size(56.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                model = iconRes,
                contentDescription = label,
                modifier = Modifier.size(28.dp),
                colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
            )
        }
    }
}
