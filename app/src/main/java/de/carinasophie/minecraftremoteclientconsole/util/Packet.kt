/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 14:12 All contents of "Packet.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.util

import com.google.gson.Gson
import com.google.gson.JsonObject

enum class PacketType(val code: String) {
    INFO("info"),
    LOGIN("login"),
    COMMAND("command"),
    CHAT("chat"),
    ERROR("error"),
    LOG("log"),
    SUCCESS("success"),
    REFRESH("refresh"),
    LOGOUT("logout"),
    CHAT_BACKUP("chat_backup"),

}


class Packet(val packetType: PacketType, val data: JsonObject) {
    companion object {

        fun fromJson(input: String): Packet {
            val json = Gson().fromJson(input, JsonObject::class.java)
            val type = PacketType.values().first { it.code == json.get("type").asString }
            return Packet(type, json.get("data").asJsonObject)
        }
    }

    fun createJsonPacket(): String {
        val json = JsonObject()
        json.addProperty("type", packetType.code)
        json.add("data", data)
        return Encoder.encode(json.toString())
    }
}