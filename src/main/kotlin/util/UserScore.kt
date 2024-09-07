package org.vikl5.util

data class UserScore(
    val userId: String,
    val username: String,
    val score: Int
) {
    /**
     * Returns a new UserScore object
     */
    fun updateTheScore(newScore: Int): UserScore {
        return copy(score = maxOf(score, newScore))
    }

    companion object {
        fun createOrUpdate(
            existingScore: UserScore?,
            userId: String,
            username: String,
            newScore: Int
        ): UserScore {
            return existingScore?.updateTheScore(newScore)
                ?: UserScore(userId, username, newScore)
        }
    }
}
