package com.migc.qatar2022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.migc.qatar2022.navigation.AppNavGraph
import com.migc.qatar2022.ui.theme.Qatar2022Theme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Qatar2022Theme {
                navHostController = rememberNavController()
                AppNavGraph(
                    navHostController = navHostController,
                    onLoadInterstitial = {
                        loadInterstitialAd()
                    },
                    onShowInterstitial = {
                        showInterstitialAd()
                    }
                )
            }
        }

    }

    private fun loadInterstitialAd() {
        if (mInterstitialAd == null) {
            InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                    }
                })
        }
    }

    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd!!.show(this)
        }

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd = null
                loadInterstitialAd()
                super.onAdDismissedFullScreenContent()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                mInterstitialAd = null
                super.onAdFailedToShowFullScreenContent(adError)
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
            }

        }
    }

}