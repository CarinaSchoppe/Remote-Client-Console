/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 7/25/22, 11:35 AM by Carina The Latest changes made by Carina on 7/25/22, 11:31 AM All contents of "PlayerSelector.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.util.Player
import de.carinasophie.minecraftremoteclientconsole.util.Utility


class PlayerSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selector)
        Utility.activeActivity = this
        Utility.chatMenuButton()
        Utility.consoleMenuButton()
        Utility.logoutButton()
        readInClients(Utility.playersList)
        sendButton()
        Utility.refresh()
        refreshButton()
    }

    private fun readInClients(playersList: List<Player>) {

    }


    private fun sendButton() {

    }

    private fun refreshButton() {
        findViewById<Button>(R.id.refreshButton).setOnClickListener {
            Utility.refresh()
        }

    }


}