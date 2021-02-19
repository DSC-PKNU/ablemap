package com.example.testnavermap

import android.app.Application
import com.example.testnavermap.api.MapsApi

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapsApi.init(getString(R.string.naver_map_sdk_client_id), getString(R.string.naver_map_sdk_client_secret))
    }
}
