package org.vikl5.service

import org.vikl5.util.UserScore

class HighScoreSorting {

    fun sortHighScore(userScores: List<UserScore>): List<UserScore> {
        return userScores.sortedByDescending { it.score }
    }
}