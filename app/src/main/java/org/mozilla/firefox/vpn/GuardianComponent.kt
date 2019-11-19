package org.mozilla.firefox.vpn

import org.mozilla.firefox.vpn.device.data.DeviceRepository
import org.mozilla.firefox.vpn.main.vpn.VpnManager
import org.mozilla.firefox.vpn.servers.data.ServerRepository
import org.mozilla.firefox.vpn.service.GuardianService
import org.mozilla.firefox.vpn.service.newInstance
import org.mozilla.firefox.vpn.user.data.UserRepository

interface GuardianComponent {
    val userRepo: UserRepository
    val deviceRepo: DeviceRepository
    val serverRepo: ServerRepository
    val vpnManager: VpnManager
}

class GuardianComponentImpl(
    private val coreComponent: CoreComponent
) : GuardianComponent, CoreComponent by coreComponent {

    private val service = GuardianService.newInstance()

    override val userRepo: UserRepository by lazy {
        UserRepository(service, coreComponent.prefs)
    }

    override val deviceRepo: DeviceRepository by lazy {
        DeviceRepository(service, coreComponent.prefs)
    }

    override val serverRepo: ServerRepository by lazy {
        ServerRepository(service, prefs)
    }

    override val vpnManager = VpnManager(app, prefs)
}