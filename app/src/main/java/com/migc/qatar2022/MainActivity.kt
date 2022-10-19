package com.migc.qatar2022

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.navigation.AppNavGraph
import com.migc.qatar2022.ui.theme.Qatar2022Theme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnUserEarnedRewardListener {

    private val LOG_TAG = "MainActivity"
    private var mInterstitialAd: InterstitialAd? = null
    private var mRewardedInterstitial: RewardedInterstitialAd? = null
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Qatar2022Theme {
                navHostController = rememberNavController()
                AppNavGraph(
                    navHostController = navHostController,
                    onLoadInterstitial = { adFormat ->
                        var unitId: String = when (adFormat) {
                            Constants.AD_UNIT_LOGIN_INTERSTITIAL_ID -> getString(R.string.ad_unit_login_interstitial_id)
                            Constants.AD_UNIT_SUBMIT_INTERSTITIAL_ID -> getString(R.string.ad_unit_submit_interstitial_id)
                            Constants.AD_UNIT_MAP_INTERSTITIAL_ID -> getString(R.string.ad_unit_map_interstitial_id)
                            else -> {
                                getString(R.string.ad_unit_login_interstitial_id)
                            }
                        }
                        loadInterstitialAd(unitId)
                    },
                    onShowInterstitial = {
                        showInterstitialAd()
                    },
                    onLoadRewardedInterstitial = {
                        loadRewardedInterstitialAd()
                    },
                    onShowRewardedInterstitial = {
                        mRewardedInterstitial?.show(this, this)
                    }
                )
            }
        }

        MobileAds.initialize(this) { initializationStatus ->
            loadRewardedInterstitialAd()
        }

    }

    override fun onUserEarnedReward(rewardItem: RewardItem) {
        Log.d(LOG_TAG, "earned $rewardItem")
    }

    private fun loadRewardedInterstitialAd() {
        RewardedInterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/5354046379", //getString(R.string.ad_unit_map_reward_id)
            AdRequest.Builder().build(),
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    mRewardedInterstitial = ad
                    mRewardedInterstitial?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdClicked() {
                            Log.d(LOG_TAG, "Ad was clicked.")
                        }

                        override fun onAdDismissedFullScreenContent() {
                            Log.d(LOG_TAG, "Ad dismissed fullscreen content.")
                            mRewardedInterstitial = null
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            Log.e(LOG_TAG, "Ad failed to show fullscreen content.")
                            mRewardedInterstitial = null
                        }

                        override fun onAdImpression() {
                            Log.d(LOG_TAG, "Ad recorded an impression.")
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d(LOG_TAG, "Ad showed fullscreen content.")
                        }
                    }
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(LOG_TAG, adError.toString())
                    mRewardedInterstitial = null
                }
            })
    }

    private fun loadInterstitialAd(unitId: String) {
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