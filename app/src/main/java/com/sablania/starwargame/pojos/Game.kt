package com.sablania.starwargame.pojos

class Game {

}

data class PlayerDetail(val player: Player, var score: Int)

data class Player(val id: Int, val name: String, val icon: String)

data class Match(val match: Int, val player1: PlayerScore, val player2: PlayerScore)

data class MatchDetails(val match: Int, val player1: PlayerDetail, val player2: PlayerDetail)

data class PlayerScore (val id:Int, val score: Int)
