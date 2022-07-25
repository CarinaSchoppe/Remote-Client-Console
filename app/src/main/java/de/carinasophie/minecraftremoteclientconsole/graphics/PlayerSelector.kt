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
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.gson.JsonObject
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.*


class PlayerSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selector)
        Utility.activeActivity = this
        Utility.chatMenuButton()
        Utility.consoleMenuButton()
        Utility.logoutButton()
        findViewById<Button>(R.id.send).setOnClickListener { sendButton() }
        Utility.refresh()
        refreshButton()

        var dropdown: Spinner = findViewById(R.id.spinner)
        var itemList = arrayOf("Mute", "Unmute", "Kick", "Ban", "Unban", "Warn")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        dropdown.adapter = adapter

    }


    companion object {
        var playerObject: Player? = null


        fun tableRowListener(tableRow: TableRow) {
            //get the table row that was clicked#
            //hexcode for light green
            tableRow.setBackgroundColor(R.color.green)
            //get all tableRows of a given tableLayout
            val tableLayout = tableRow.parent as TableLayout
            for (tableRows in tableLayout.children.toList().subList(1, tableLayout.childCount)) {
                //get the tableRow that is currently selected
                val selectedTableRow = tableRows as TableRow
                //set the background color of the selected tableRow to white

                if (!((selectedTableRow.children.first() as TextView).text.equals((tableRow.children.first() as TextView).text))) {
                    selectedTableRow.setBackgroundColor(0xFFFFFFFF.toInt())
                }
            }

            playerObject = Utility.playersList.find { it.name == (tableRow.children.first() as TextView).text.toString() && it.ping.toString() == (tableRow.children.last() as TextView).text.toString() }!!

        }
    }

    private fun reasonCheck(): Boolean {
        if (findViewById<TextView>(R.id.reasonText).text == null) {
            PopUp.createBadPopUp(this, "Missing", "Missing a reason!")
        } else if (playerObject == null) {

            PopUp.createBadPopUp(this, "Missing", "Missing a selection!")
            return true
        }
        return false
    }

    private fun sendButton() {

        when (findViewById<Spinner>(R.id.spinner).selectedItem.toString()) {
            "Kick" -> {
                if (reasonCheck()) return
                val json = JsonObject()
                json.addProperty("command", "/kick ${playerObject!!.name} ${findViewById<TextView>(R.id.reasonText).text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }
            }
            "Mute" -> {
                if (reasonCheck()) return
                val json = JsonObject()
                json.addProperty("command", "/mute ${playerObject!!.name} ${findViewById<TextView>(R.id.reasonText).text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()
            }
            "Unmute" -> {
                val json = JsonObject()
                json.addProperty("command", "/unmute ${playerObject!!.name}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()


            }
            "Ban" -> {
                if (reasonCheck()) return
                val json = JsonObject()
                json.addProperty("command", "/ban ${playerObject!!.name} ${findViewById<TextView>(R.id.reasonText).text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()

            }
            "Unban" -> {
                val json = JsonObject()
                json.addProperty("command", "/unban ${playerObject!!.name}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()
            }

            "Warn" -> {
                if (reasonCheck()) return
                val json = JsonObject()
                json.addProperty("command", "/warn ${playerObject!!.name} ${findViewById<TextView>(R.id.reasonText).text}")
                Thread {
                    Client.instance.writer.println(Packet(PacketType.COMMAND, json).createJsonPacket())
                }.start()

            }
        }
    }

    private fun refreshButton() {
        findViewById<Button>(R.id.refreshButton).setOnClickListener {
            Utility.refresh()
        }

    }


}