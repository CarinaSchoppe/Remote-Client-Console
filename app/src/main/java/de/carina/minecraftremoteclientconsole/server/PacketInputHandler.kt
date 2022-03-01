package de.carina.minecraftremoteclientconsole.server

import android.content.Intent
import android.widget.TextView
import de.carina.minecraftremoteclientconsole.R
import de.carina.minecraftremoteclientconsole.grafics.Console
import de.carina.minecraftremoteclientconsole.util.Packet
import de.carina.minecraftremoteclientconsole.util.PacketType
import de.carina.minecraftremoteclientconsole.util.PopUp

object PacketInputHandler {

    fun handlePacket(packet: Packet) {
        println("Server has send: ${packet.createJsonPacket()}")
        when (packet.packetType) {
            PacketType.LOGIN -> {
                if (!packet.data.get("magic").asString.equals(Client.instance.magicCode)) {
                    PopUp.createBadPopUp(PopUp.activeActivity, "Wrong Server", "The server you are trying to connect to is not aviable.")
                    Client.instance.disconnect()
                }
            }
            PacketType.ERROR -> {
                if (packet.data.get("action").asString.equals("disconnect")) {
                    PopUp.createBadPopUp(PopUp.activeActivity, "Error", packet.data.get("message").asString)
                    Client.instance.disconnect()
                }
            }
            PacketType.SUCCESS -> {
                PopUp.activeActivity.startActivity(Intent(PopUp.activeActivity, Console::class.java))
            }
            PacketType.LOG -> {
                val console = PopUp.activeActivity.findViewById<TextView>(R.id.consoleOut)
                Console.text += packet.data.get("log").asString.replace("§", "&") + "\n"
                if (console != null) {
                    console.setText(Console.text)
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
                    PopUp.createGoodPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                else if ((packet.data.get("info").asJsonObject.get("type").asString == "fail"))
                    PopUp.createBadPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)
                else if ((packet.data.get("info").asJsonObject.get("type").asString == "warn"))
                    PopUp.createBadPopUp(PopUp.activeActivity, packet.data.get("info").asJsonObject.get("title").asString, packet.data.get("info").asJsonObject.get("text").asString)

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