/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "Utility.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.util

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.graphics.Chat
import de.carinasophie.minecraftremoteclientconsole.graphics.Console
import de.carinasophie.minecraftremoteclientconsole.graphics.Login
import de.carinasophie.minecraftremoteclientconsole.graphics.PlayerMenu

object Utility {
    var consoleText = """"""
    var chat = """"""
    lateinit var activeActivity: AppCompatActivity

    fun chatMenuButton() {
        val button = activeActivity.findViewById<Button>(R.id.chatButton)
        button.setOnClickListener {
            activeActivity.startActivity(Intent(activeActivity, Chat::class.java))
        }
    }

    fun playerMenuButton() {
        val button = activeActivity.findViewById<Button>(R.id.playerMenuButton)
        button.setOnClickListener {
            activeActivity.startActivity(Intent(activeActivity, PlayerMenu::class.java))
        }
    }

    fun consoleMenuButton() {
        val button = activeActivity.findViewById<Button>(R.id.consoleButton)
        button.setOnClickListener {
            activeActivity.startActivity(Intent(activeActivity, Console::class.java))
        }
    }

    fun logout() {
        val button = activeActivity.findViewById<Button>(R.id.logout)
        button.setOnClickListener {
            Client.instance.logout()
            activeActivity.startActivity(Intent(activeActivity, Login::class.java))
        }
    }
}