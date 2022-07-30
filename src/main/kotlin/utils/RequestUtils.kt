package utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RequestUtils {

    fun getJsonString(urlToRead: String, token: String): String {
        val result = StringBuilder()
        val url = URL(urlToRead)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.doOutput = true
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36")
        conn.setRequestProperty("Authorization", token)
        BufferedReader(
            InputStreamReader(conn.inputStream)
        ).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result.append(line)
            }
        }
        return result.toString()
    }

    fun getRequestCode(urlToRead: String, token: String): Int {
        val url = URL(urlToRead)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.doOutput = true
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36")
        conn.setRequestProperty("Authorization", token)
        conn.connect()
        return conn.responseCode
    }


}