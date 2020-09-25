package com.sablania.starwargame.repositories

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sablania.starwargame.pojos.Match
import com.sablania.starwargame.pojos.Player
import java.io.IOException
import java.io.InputStream

class GameRepository(private val application: Application) {

    fun fetchPlayers(): ArrayList<Player> {
        val typeToken =
            object : TypeToken<ArrayList<Player>>(){}.type
        return Gson().fromJson(loadJSONFromAsset("StarWarsPlayers.json"), typeToken)
    }

    fun loadJSONFromAsset(fileName:String): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = application.getAssets().open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun fetchMatches(): java.util.ArrayList<Match> {
        val typeToken =
            object : TypeToken<ArrayList<Match>>() {}.type
        return Gson().fromJson(loadJSONFromAsset("StarWarsMatches.json"), typeToken)
    }

}
