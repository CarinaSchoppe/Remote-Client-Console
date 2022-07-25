/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 11:55 All contents of "Encoder.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.util

import java.util.*

object Encoder {

    fun encode(input: String): String {
        return Base64.getEncoder().encodeToString(input.toByteArray())
    }

    fun decode(input: String): String {
        return String(Base64.getDecoder().decode(input))
    }

}