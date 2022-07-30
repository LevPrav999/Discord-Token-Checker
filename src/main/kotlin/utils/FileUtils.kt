package utils

import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class FileUtils {
    private val dirName = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss"))

    fun writeInvalid(token: String){
        val file = File(dirName)
        file.mkdirs()
        val fileW = FileWriter(dirName+"/tokens-invalid.txt",true)
        fileW.write(token + "\n")

        fileW.close()
    }

    fun writeLimited(token: String){
        val file = File(dirName)
        file.mkdirs()

        val fileW = FileWriter(dirName+"/tokens-limited.txt",true)
        fileW.write(token + "\n")
        fileW.close()
    }

    fun writeValid(token: String){
        val file = File(dirName)
        file.mkdirs()

        val fileW = FileWriter(dirName+"/tokens-valid.txt",true)
        fileW.write(token + "\n")
        fileW.close()
    }

    fun writeValidInfo(token: String, info: JSONObject, cards: Int){
        val file = File(dirName)
        file.mkdirs()

        val fileW = FileWriter(dirName+"/tokens-valid-info.txt",true)

        fileW.write("$token | username: ${info.get("username")} | phone: ${info.get("phone")} | " +
                "email: ${info.get("email")} | mfa: ${info.get("mfa_info")} | verified: ${info.get("verified")} | Cards: ${cards}\n")
        fileW.close()
    }
}