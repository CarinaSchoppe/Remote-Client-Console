package de.carina.minecraftremoteclientconsole.util

data class Coordinates(val x: Int, val y: Int, val z: Int) {
    override fun toString(): String {
        return "(X:$x, Y:$y, Z:$z)"
    }
}

data class Player(val name: String, val health: Int, val food: Int, val coordinates: Coordinates, val world: String, val gamemode: String, val level: Int, val ping: Int) {
    override fun toString(): String {
        return "Player(name='$name', health=$health, food=$food, coordinates=$coordinates, world='$world', gamemode='$gamemode', level=$level, ping=$ping)"
    }
}