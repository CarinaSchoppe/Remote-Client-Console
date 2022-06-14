/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "Console.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.Packet
import de.carinasophie.minecraftremoteclientconsole.util.PacketType
import de.carinasophie.minecraftremoteclientconsole.util.Utility

class Console : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        Utility.activeActivity = this

        //find console out and command
        val consoleOut = findViewById<TextView>(R.id.consoleOut)
        val command = findViewById<EditText>(R.id.command)

        //load saveInstanceState
        if (savedInstanceState != null) {
            consoleOut.text = savedInstanceState.getString("consoleText")
            command.setText(savedInstanceState.getString("commandText"))
        }
        sendButton()
        Utility.logout()
        Utility.chatMenuButton()
        Utility.playerMenuButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //find console out and command
        val consoleOut = findViewById<TextView>(R.id.consoleOut)
        val command = findViewById<EditText>(R.id.command)
        //save the command text and the console out
        outState.putString("commandText", command?.text.toString())
        outState.putString("consoleText", consoleOut?.text.toString())

    }

    private fun sendButton() {
        val button = findViewById<Button>(R.id.send)
        button.setOnClickListener {
            val window = findViewById<EditText>(R.id.command)
            if (window.text.isNotEmpty()) {
                val json = JsonObject()
                json.addProperty("command", "${window.text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()
                window.setText("command")
            }
        }
    }


}