package de.carina.minecraftremoteclientconsole.server

object PacketInputHandler {

//    fun handlePacket(packet: Packet) {
//        println("Server has send: ${packet.createJsonPacket()}")
//        when (packet.packetType) {
//            PacketType.LOGIN -> {
//                if (!packet.data.get("magic").asString.equals(Client.instance.magicCode)) {
//                    Platform.runLater { Dialog.show("Wrong Server", "Error", Alert.AlertType.ERROR) }
//                    Client.instance.disconnect()
//                }
//            }
//            PacketType.ERROR -> {
//                if (packet.data.get("action").asString.equals("disconnect")) {
//                    Platform.runLater { Dialog.show(packet.data.get("message").asString, "Error", Alert.AlertType.ERROR) }
//                    Client.instance.disconnect()
//                }
//            }
//            PacketType.SUCCESS -> {
//                Platform.runLater {
//                    Console().start(Login.stage.scene.window as Stage)
//                }
//            }
//            PacketType.LOG -> {
//                Platform.runLater {
//                    Console.text += packet.data.get("log").asString.replace("§", "&") + "\n"
//                    if (Console.instance != null) {
//                        Console.instance!!.consoleWindow.text = Console.text
//                    }
//                }
//            }
//            PacketType.REFRESH -> {
//                Platform.runLater {
//                    if (Selection.instance != null) {
//                        refresh(packet)
//                    }
//                }
//            }
//            PacketType.INFO -> {
//                Platform.runLater {
//                    if (packet.data.get("info").asJsonObject.get("type").asString == "success")
//                        Dialog.show(packet.data.get("info").asJsonObject.get("text").asString, packet.data.get("info").asJsonObject.get("title").asString, Alert.AlertType.INFORMATION)
//                    else if ((packet.data.get("info").asJsonObject.get("type").asString == "fail"))
//                        Dialog.show(packet.data.get("info").asJsonObject.get("text").asString, packet.data.get("info").asJsonObject.get("title").asString, Alert.AlertType.ERROR)
//                    else if ((packet.data.get("info").asJsonObject.get("type").asString == "warn"))
//                        Dialog.show(packet.data.get("info").asJsonObject.get("text").asString, packet.data.get("info").asJsonObject.get("title").asString, Alert.AlertType.WARNING)
//
//                }
//            }
//
//            PacketType.CHAT -> {
//                Platform.runLater {
//                    Chat.text += packet.data.get("player").asString + " >> " + packet.data.get("message").asString + "\n"
//                    if (Chat.instance != null) {
//                        Chat.instance.consoleWindow.text = Chat.text
//                    }
//                }
//            }
//            PacketType.LOGOUT -> {
//                Client.instance.disconnect()
//            }
//        }
//
//    }
//
//    private fun refresh(packet: Packet) {
//        val myType = object : TypeToken<List<Player>>() {}.type
//        val test = Gson().fromJson<List<Player>>(packet.data.get("players"), myType)
//        Selection.players = test
//        Selection.instance!!.update()
//    }


}