/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "PacketInputHandler.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.client

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.graphics.Chat
import de.carinasophie.minecraftremoteclientconsole.graphics.Console
import de.carinasophie.minecraftremoteclientconsole.graphics.PlayerSelector
import de.carinasophie.minecraftremoteclientconsole.util.*

object PacketInputHandler {

    fun handlePacket(packet: Packet) {
        when (packet.packetType) {
            PacketType.LOGIN -> {
                if (!packet.data.get("magic").asString.equals(Client.instance.magicCode)) {
                    PopUp.createBadPopUp(Utility.activeActivity, "Wrong Server", "The server you are trying to connect to is not available.")
                    Client.instance.disconnect()
                }
            }
            PacketType.ERROR -> {
                if (packet.data.get("action").asString.equals("disconnect")) {
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createBadPopUp(Utility.activeActivity, "Disconnected", "You have been disconnected from the server.")
                    }
                    Client.instance.disconnect()
                }
            }
            PacketType.SUCCESS -> {
                Handler(Looper.getMainLooper()).post {

                Utility.activeActivity.startActivity(Intent(Utility.activeActivity, Console::class.java))
                    val console = Utility.activeActivity.findViewById<TextView>(R.id.consoleOut)
                    console?.text = Utility.consoleText

                }
            }
            PacketType.LOG -> {
                Utility.consoleText += packet.data.get("log").asString.replace("§", "&") + "\n"
                Handler(Looper.getMainLooper()).post {
                    Utility.activeActivity.findViewById<TextView>(R.id.consoleOut)?.text = Utility.consoleText
                }
            }

            PacketType.INFO -> {
                if (packet.data.get("info").asJsonObject.get("type").asString == "success")
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createGoodPopUp(Utility.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                    }
                else if ((packet.data.get("info").asJsonObject.get("type").asString == "fail") || (packet.data.get("info").asJsonObject.get("type").asString == "warn"))
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createBadPopUp(Utility.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                    }
            }

            PacketType.CHAT -> {
                Handler(Looper.getMainLooper()).post {
                    Utility.chat += packet.data.get("player").asString + " >> " + packet.data.get("message").asString + "\n"
                    if (Utility.activeActivity is Chat) {
                        Utility.activeActivity.findViewById<TextView>(R.id.chatOut)?.text = Utility.chat
                    }
                }
            }

            PacketType.CHAT_BACKUP -> {
                Handler(Looper.getMainLooper()).post {
                    if (!packet.data.get("message").asString.equals(""))
                        Utility.chat += packet.data.get("message").asString + "\n"
                    if (Utility.activeActivity is Chat) {
                        Utility.activeActivity.findViewById<TextView>(R.id.chatOut)?.text = Utility.chat
                    }
                }
            }
            PacketType.LOGOUT -> {
                Client.instance.disconnect()
            }

            PacketType.REFRESH -> {
                val dataType = object : TypeToken<List<Player>>() {}.type
                val playersList = Gson().fromJson<MutableList<Player>>(packet.data.get("players"), dataType)
                Utility.playersList = playersList
                Handler(Looper.getMainLooper()).post {
                    var table = Utility.activeActivity.findViewById<TableLayout>(R.id.tablePlayers)
                    deleteRows(table)
                    for (player in Utility.playersList) {
                        val tableRow = TableRow(Utility.activeActivity)
                        tableRow.isClickable = true
                        tableRow.setOnClickListener { PlayerSelector.tableRowListener(tableRow) }
                        tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.name
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.world
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.health.toString()
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.food.toString()
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.coordinates.toString()
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.gamemode
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })

                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.level.toString()
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })
                        tableRow.addView(TextView(Utility.activeActivity).apply {
                            text = player.ping.toString()
                            textSize = 20f
                            setPadding(0, 0, 0, 0)
                        })

                        //add the row to the table

                        table?.addView(tableRow)
                    }
                }
            }
            else -> {
                throw Exception("Unknown packet type")
            }
        }

    }

    private fun deleteRows(table: TableLayout) {
        for (i in 1 until table.childCount) {
            table.removeViewAt(i)
        }
    }


}