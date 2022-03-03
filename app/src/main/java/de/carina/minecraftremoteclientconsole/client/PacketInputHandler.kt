package de.carina.minecraftremoteclientconsole.client

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import de.carina.minecraftremoteclientconsole.R
import de.carina.minecraftremoteclientconsole.grafics.Console
import de.carina.minecraftremoteclientconsole.util.Packet
import de.carina.minecraftremoteclientconsole.util.PacketType
import de.carina.minecraftremoteclientconsole.util.PopUp
import de.carina.minecraftremoteclientconsole.util.Utility

object PacketInputHandler {

    fun handlePacket(packet: Packet) {
        when (packet.packetType) {
            PacketType.LOGIN -> {
                if (!packet.data.get("magic").asString.equals(Client.instance.magicCode)) {
                    PopUp.createBadPopUp(PopUp.activeActivity, "Wrong Server", "The server you are trying to connect to is not aviable.")
                    Client.instance.disconnect()
                }
            }
            PacketType.ERROR -> {
                if (packet.data.get("action").asString.equals("disconnect")) {
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createBadPopUp(PopUp.activeActivity, "Disconnected", "You have been disconnected from the server.")
                    }
                    Client.instance.disconnect()
                }
            }
            PacketType.SUCCESS -> {
                Handler(Looper.getMainLooper()).post {
                    PopUp.activeActivity.startActivity(Intent(PopUp.activeActivity, Console::class.java))
                    val console = PopUp.activeActivity.findViewById<TextView>(R.id.consoleOut)
                    console.setText(Utility.consoleText)
                }
            }
            PacketType.LOG -> {
                Utility.consoleText += packet.data.get("log").asString.replace("§", "&") + "\n"
                Handler(Looper.getMainLooper()).post {
                    val console = PopUp.activeActivity.findViewById<TextView>(R.id.consoleOut)
                    if (console != null) {
                        console.setText(Utility.consoleText)
                    }
                }
            }

//            PacketType.REFRESH -> {
//                Platform.runLater {
//                    if (Selection.instance != null) {
//                        refresh(packet)
//                    }
//                }
//            }
            PacketType.INFO -> {
                if (packet.data.get("info").asJsonObject.get("type").asString == "success")
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createGoodPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                    }
                else if ((packet.data.get("info").asJsonObject.get("type").asString == "fail"))
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createBadPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                    }
                else if ((packet.data.get("info").asJsonObject.get("type").asString == "warn"))
                    Handler(Looper.getMainLooper()).post {
                        PopUp.createBadPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                    }
            }

//            PacketType.CHAT -> {
//                Platform.runLater {
//                    Chat.text += packet.data.get("player").asString + " >> " + packet.data.get("message").asString + "\n"
//                    if (Chat.instance != null) {
//                        Chat.instance.consoleWindow.text = Chat.text
//                    }
//                }
//            }
            PacketType.LOGOUT -> {
                Client.instance.disconnect()
            }
        }

    }

//    private fun refresh(packet: Packet) {
//        val myType = object : TypeToken<List<Player>>() {}.type
//        val test = Gson().fromJson<List<Player>>(packet.data.get("players"), myType)
//        Selection.players = test
//        Selection.instance!!.update()
//    }


}