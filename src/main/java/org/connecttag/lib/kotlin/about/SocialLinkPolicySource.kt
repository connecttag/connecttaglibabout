package org.connecttag.lib.kotlin.about

interface SocialLinkPolicySource {
    fun canOpenGooglePlayLinks(): Boolean
}

object AllowAllSocialLinkPolicySource : SocialLinkPolicySource {
    override fun canOpenGooglePlayLinks(): Boolean = true
}
