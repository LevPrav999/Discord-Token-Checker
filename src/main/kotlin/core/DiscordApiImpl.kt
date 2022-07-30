package core

import com.google.gson.JsonParser
import dto.DiscordApi
import org.json.JSONObject
import utils.RequestUtils

class DiscordApiImpl : DiscordApi {
    private val baseUrl: String = "https://discord.com/api/v9/users/@me";
    private val utils: RequestUtils = RequestUtils()

    override fun getStatus(token: String): Int = utils.getRequestCode("$baseUrl/library", token)

    override fun getCards(token: String): Int {
        val jsonString: String = utils.getJsonString("$baseUrl/billing/payment-sources", token)
        val parser = JsonParser()
        val arr = parser.parse(jsonString).asJsonArray
        return arr.size()

    }

    override fun getInfoToken(token: String): JSONObject {
        val result = JSONObject()
        val jsonString: String = utils.getJsonString(baseUrl, token)
        val parser = JsonParser()
        val json = parser.parse(jsonString).asJsonObject

        if(json.has("phone"))
            result.put("phone", json.get("phone").asString)
        else
            result.put("phone", "Not entered")

        result.put("username", json.get("username").asString+json.get("discriminator").asString)
        result.put("email", json.get("email").asString)
        result.put("mfa_info", json.get("mfa_enabled").asString)
        result.put("verified", json.get("verified").asString)

        return result

    }

}