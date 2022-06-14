package de.carinasophie.minecraftremoteclientconsole.util

import java.util.*

object Encoder {

    fun encode(input: String): String {
        val encode = Base64.getEncoder().encodeToString(input.toByteArray())
        return encode
    }

    fun decode(input: String): String {
        return String(Base64.getDecoder().decode(input))
    }

}