package de.carina.minecraftremoteclientconsole.client

import com.google.gson.JsonObject
import de.carina.minecraftremoteclientconsole.util.Encoder
import de.carina.minecraftremoteclientconsole.util.Packet
import de.carina.minecraftremoteclientconsole.util.PacketType
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket
import java.nio.charset.StandardCharsets

class Client(private val name: String, private val ip: String, private val port: Int, private val password: String) {

    private var socket: Socket? = null
    private lateinit var reader: BufferedReader
    lateinit var writer: PrintWriter
    val magicCode = "mc2912"

    companion object {
        lateinit var instance: Client
    }

    fun connect(): Boolean {
        try {
            Thread({
                println("Connecting to $ip:${port}")
                socket = Socket(ip, port)
                start()
                println("Client $name connected to $ip:$port")
            }).start()
            return true
        } catch (e: Exception) {
            println(e.printStackTrace())
            return false
        }
    }


    fun logout() {
        val json = JsonObject()
        Thread({
            writer.println(Packet(PacketType.LOGOUT, json).createJsonPacket())
        }).start()
    }

    fun disconnect() {
        if (socket != null) {
            socket!!.close()
            socket = null
            println("Client \"$name\" disconnected from $ip:$port")
        }
    }

    fun start() {
        if (socket == null)
            return
        if (socket!!.isConnected) {
            instance = this
            reader = BufferedReader(InputStreamReader(socket!!.getInputStream(), StandardCharsets.UTF_8), 16384)
            writer = PrintWriter(OutputStreamWriter(socket!!.getOutputStream(), StandardCharsets.UTF_8), true)
            login()
            read()
        }
    }

    private fun login() {
        val json = JsonObject()
        val info = JsonObject()
        info.addProperty("username", name)
        info.addProperty("password", password)
        json.addProperty("magic", magicCode)
        json.add("login", info)
        writer.println(Packet(PacketType.LOGIN, json).createJsonPacket())

    }

    private fun read() {
        Thread {
            while (true) {
                var input: String?
                try {
                    input = Encoder.decode(reader.readLine())
                } catch (e: Exception) {
                    disconnect()
                    return@Thread
                }
                if (input != null) {
                    PacketInputHandler.handlePacket(Packet.fromJson(input))
                } else {
                    disconnect()
                    return@Thread
                }
            }
        }.start()
    }

}