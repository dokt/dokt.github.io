package app.dokt.generator.building.gradle

import app.dokt.app.ApplicationService
import app.dokt.generator.DOKT_SETTINGS_PLUGIN_ID
import app.dokt.generator.REFRESH_VERSIONS_PLUGIN_ID
import app.dokt.generator.vDokt
import app.dokt.generator.vRefreshVersions
import app.dokt.infra.Logger

@ApplicationService
object SettingsInitializer : Logger({}) {
    fun SettingsInitialization.applyPlugins() {
        applyPlugin(DOKT_SETTINGS_PLUGIN_ID, vDokt)
        applyPlugin(REFRESH_VERSIONS_PLUGIN_ID, vRefreshVersions)
    }
}