package org.connecttag.lib.kotlin.about.compose

import org.connecttag.lib.kotlin.about.AboutAppPresentationMode

object AboutAppPresentationModeParser {
    fun parse(
        value: String?,
        mixedModeSelector: () -> AboutAppPresentationMode = { AboutAppPresentationMode.Dialog },
    ): AboutAppPresentationMode? {
        val appMode = org.connecttag.lib.kotlin.about.app.AboutAppPresentationModeParser.parse(value) {
            when (mixedModeSelector()) {
                AboutAppPresentationMode.Dialog -> org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.Dialog
                AboutAppPresentationMode.BottomSheet -> org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.BottomSheet
                AboutAppPresentationMode.FullScreen -> org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.FullScreen
            }
        } ?: return null

        return when (appMode) {
            org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.Dialog -> AboutAppPresentationMode.Dialog
            org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.BottomSheet -> AboutAppPresentationMode.BottomSheet
            org.connecttag.lib.kotlin.about.app.AboutAppPresentationMode.FullScreen -> AboutAppPresentationMode.FullScreen
        }
    }
}
