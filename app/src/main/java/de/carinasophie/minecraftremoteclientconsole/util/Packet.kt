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