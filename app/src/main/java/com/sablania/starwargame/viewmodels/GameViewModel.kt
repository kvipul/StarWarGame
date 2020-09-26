package com.sablania.starwargame.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sablania.starwargame.pojos.Match
import com.sablania.starwargame.pojos.MatchDetails
import com.sablania.starwargame.pojos.Player
import com.sablania.starwargame.pojos.PlayerDetail
import com.sablania.starwargame.repositories.GameRepository

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application)
    private lateinit var playerList: ArrayList<Player>
    private lateinit var matchList: ArrayList<Match>
    private val map = HashMap<Int, PlayerDetail>()

    //can be exposed as livedata instead of mutablelivedata
    val playersLiveData = MutableLiveData<ArrayList<PlayerDetail>>()
    val matchesLiveData = MutableLiveData<ArrayList<MatchDetails>>()

    fun init() {
        playerList = gameRepository.fetchPlayers()
        matchList = gameRepository.fetchMatches()

        for (player in playerList) {
            map[player.id] = PlayerDetail(player, 0)
        }
        for (match in matchList) {
            val p1 = match.player1
            val p2 = match.player2
            if (p1.score == p2.score) {
                map[p1.id]!!.score += 1
                map[p2.id]!!.score += 1
            } else if (p1.score > p2.score) {
                map[p1.id]!!.score += 3
            } else {
                map[p2.id]!!.score += 3
            }
        }
    }


    fun fetchPlayersData() {
        val list = ArrayList<PlayerDetail>()
        map.values.forEach {
            list.add(it)
        }
        playersLiveData.postValue(list)
    }

    fun fetchMatchData(playerId: Int) {
        val list = ArrayList<MatchDetails>()
        matchList.forEach { match ->
            val p1 = match.player1
            val p2 = match.player2

            if (playerId == p1.id || playerId == p2.id) {
                list.add(
                    MatchDetails(
                        match.match,
                        map[match.player1.id]!!,
                        map[match.player2.id]!!,
                        match.player1.score,
                        match.player2.score
                    )
                )
            }
        }
        matchesLiveData.postValue(list)
    }

}
