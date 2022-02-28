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
        val confirmButton = activity.findViewById<Button>(R.id.confirm)
        popDialog.setContentView(R.layout.pop_up)
        activity.findViewById<TextView>(R.id.infoText).text = message
        activity.findViewById<TextView>(R.id.headLine).text = title

        popDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirmButton.setOnClickListener {
            popDialog.dismiss()
        }
    }

}