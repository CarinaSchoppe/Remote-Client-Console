package de.carina.minecraftremoteclientconsole.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import de.carina.minecraftremoteclientconsole.R

object PopUp {

    lateinit var activeActivity: Activity
    fun cratePopUp(activity: Activity, title: String, message: String) {
        var popDialog = Dialog(activity)
        popDialog.setContentView(R.layout.pop_up)
        popDialog.setCancelable(false)
        popDialog.show()
        val confirmButton = popDialog.findViewById<Button>(R.id.confirm)
        val messageText = popDialog.findViewById<TextView>(R.id.messageText)
        var heading = popDialog.findViewById<TextView>(R.id.headLineText)
        println("" + messageText + " " + heading + "a")
        messageText.text = message
        heading.text = title
        popDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirmButton.setOnClickListener {
            popDialog.dismiss()
        }
    }

}