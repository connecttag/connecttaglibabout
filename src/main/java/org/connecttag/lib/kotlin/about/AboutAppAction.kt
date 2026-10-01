package org.connecttag.lib.kotlin.about

sealed interface AboutAppAction {
    data class OpenUrl(val url: String) : AboutAppAction
    data class SendEmail(val email: String) : AboutAppAction
    data class OpenPlayStore(val packageName: String) : AboutAppAction
}

fun interface AboutAppActionDispatcher {
    fun dispatch(action: AboutAppAction)
}
