/*
 * Copyright Notice for MinecraftRemoteClientConsole
 * Copyright (c) at Carina Sophie Schoppe 2022
 * File created on 14.06.22, 15:35 by Carina The Latest changes made by Carina on 14.06.22, 15:34 All contents of "Login.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinasophie.minecraftremoteclientconsole.graphics

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import de.carinasophie.minecraftremoteclientconsole.R
import de.carinasophie.minecraftremoteclientconsole.client.Client
import de.carinasophie.minecraftremoteclientconsole.util.PopUp
import de.carinasophie.minecraftremoteclientconsole.util.Utility


class Login : AppCompatActivity() {

    companion object {
        lateinit var client: Client
    }

    lateinit var mAdView: AdView
    private var TAG = "MainActivity"
    private var mInterstitialAd: InterstitialAd? = null
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var ip = findViewById<EditText>(R.id.ip).text
        val port = findViewById<EditText>(R.id.port).text
        val userName = findViewById<EditText>(R.id.username).text
        val password = findViewById<EditText>(R.id.password).text
        outState.putString("username", userName.toString())
        outState.putString("password", password.toString())
        outState.putString("ip", ip.toString())
        outState.putString("port", port.toString())


    }


    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Utility.activeActivity = this
        val ip = findViewById<EditText>(R.id.ip)
        val port = findViewById<EditText>(R.id.port)
        val userName = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        Utility.loggedIn = false
        var adRequestbefore = AdRequest.Builder().build()

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequestbefore, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }


            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }

        MobileAds.initialize(this)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        if (savedInstanceState != null) {
            ip.setText(savedInstanceState.getString("ip"))
            port.setText(savedInstanceState.getString("port"))
            userName.setText(savedInstanceState.getString("username"))
            password.setText(savedInstanceState.getString("password"))
        }



        loginButton()


    }

    private fun loginButton() {
        var ip: Any = findViewById<EditText>(R.id.ip).text
        val port = findViewById<EditText>(R.id.port).text
        val userName = findViewById<EditText>(R.id.username).text
        val password = findViewById<EditText>(R.id.password).text
        /*      var ip = "10.0.2.2"
              val port = 8080
              val userName = "test"
              val password = "test"*/
        val loginButton = findViewById<Button>(R.id.loginButton)
        if (ip == "localhost")
            ip = "10.0.2.2"
        loginButton.setOnClickListener {
            try {
                port.toString().toInt()
            } catch (e: Exception) {
                PopUp.createBadPopUp(this, "Port Error", "The port must be a number")
                return@setOnClickListener
            }
            val instance = Client(ip = ip.toString(), port = port.toString().toInt(), name = userName.toString(), password = password.toString())
            client = instance
            if (!client.connect()) {
                PopUp.createBadPopUp(this, "Error", "Could not connect to server")
            }


        }
    }
}