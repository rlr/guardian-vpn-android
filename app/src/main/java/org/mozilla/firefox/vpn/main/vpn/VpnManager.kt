package org.mozilla.firefox.vpn.main.vpn

import org.mozilla.firefox.vpn.device.data.CurrentDevice
import org.mozilla.firefox.vpn.main.vpn.domain.VpnStateProvider
import org.mozilla.firefox.vpn.servers.data.ServerInfo

interface VpnManager : VpnStateProvider {

    fun isGranted(): Boolean

    suspend fun connect(server: ServerInfo, connectionConfig: ConnectionConfig)

    suspend fun switch(oldServer: ServerInfo, newServer: ServerInfo, connectionConfig: ConnectionConfig)

    suspend fun disconnect()

    suspend fun shutdownConnection()

    fun isConnected(): Boolean

    fun getDuration(): Long
}

data class ConnectionConfig(
    val currentDevice: CurrentDevice,
    val includedApps: List<String> = emptyList(),
    val excludeApps: List<String> = emptyList()
)
