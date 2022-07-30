package dto

import org.json.JSONObject

interface DiscordApi {
    fun getStatus(token: String): Int
    fun getCards(token: String): Int
    fun getInfoToken(token: String): JSONObject
}