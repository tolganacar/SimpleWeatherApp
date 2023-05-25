package com.tolganacar.simpleweatherapp.util

import android.content.Context

interface ResourceProvider {
    fun getAsset(path: String): String
}

class ResourceProviderImpl(val context: Context) : ResourceProvider {
    override fun getAsset(path: String): String {
        return context.assets.open(path)
            .bufferedReader()
            .use { it.readText() }
    }
}
