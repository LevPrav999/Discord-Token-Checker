package core

import com.github.mm.coloredconsole.colored
import dto.Checker
import utils.FileUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.exists


class CheckerImpl : Checker{
    private val api: DiscordApiImpl = DiscordApiImpl()
    private val fileUtils: FileUtils = FileUtils()

    override fun checkFile(path: String) {
        if(!Paths.get(path).exists()){
            colored {
                println("[ERROR] ".red + "Incorrect file path")
            }
            return
        }

        val file = File(path)
        try {
            BufferedReader(FileReader(file)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    line?.let { checkToken(it.replace("\n", "")) }
                }
            }
        } catch (_: IOException) {}
    }

    override fun checkToken(token: String){
        if(token == "")
            return
        when(val statusCode: Int = api.getStatus(token)){
            401 -> {
                colored {
                    println("[BAD: ${statusCode}]".red + " | $token")
                }
                fileUtils.writeInvalid(token)
            }
            403 -> {
                colored {
                    println("[LIMITED: ${statusCode}]".cyan + " | $token")
                }
                fileUtils.writeLimited(token)
            }
            else -> {
                colored {
                    println("[VALID: ${statusCode}]".green.bold + " | $token")
                }
                fileUtils.writeValid(token)

                val cards = api.getCards(token)
                val info = api.getInfoToken(token)

                fileUtils.writeValidInfo(token, info, cards)
            }
        }
    }
}