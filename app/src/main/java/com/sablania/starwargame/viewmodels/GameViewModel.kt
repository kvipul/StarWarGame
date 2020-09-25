package com.sablania.starwargame.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sablania.starwargame.pojos.Match
import com.sablania.starwargame.pojos.Player
import com.sablania.starwargame.pojos.PlayerDetail
import com.sablania.starwargame.repositories.GameRepository

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application)
    private lateinit var playerList : ArrayList<Player>
    private lateinit var matchList : ArrayList<Match>
    private val map = HashMap<Int, PlayerDetail>()

    val playersLiveData = MutableLiveData<ArrayList<PlayerDetail>>()

    fun init() {
        playerList = gameRepository.fetchPlayers()
        matchList = gameRepository.fetchMatches()

        for (player in playerList) {
            map[player.id] = PlayerDetail(player, 0)
        }
    }


    fun fetchPlayersData() {
        val list = ArrayList<PlayerDetail>()
        map.values.forEach {
            list.add(it)
        }
        playersLiveData.postValue(list)
    }

}
