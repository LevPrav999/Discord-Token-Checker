import com.github.mm.coloredconsole.colored
import core.CheckerImpl
import java.util.*
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    colored {
        val style1 = green.bold + black.bg
        val style2 = red.bold + black.bg
        val inputStyle = cyan.bold
        val linkStyle = yellow.underline.bold.italic + black.bg
        println("Hi! If you're reading this, then you're probably using my token checker!"(style1))
        println("You can contact me via Telegram: "(style1) + "@timofey_prostov"(linkStyle))
        println("The source code is on GitHub: "(style1) + "https://github.com/LevPrav/Discord-Token-Checker"(linkStyle))

        println()

        println("Select the desired action:"(style2))
        println("1 - Check one token"(style2))
        println("2 - Check the token file"(style2))
        println("3 - Exit"(style2))
        println()
        print("> "(inputStyle))

    }
    val selected = input.nextInt()

    when(selected){
        1 -> {
            println("Enter the token: ")
            while (true){
                if(input.hasNext()){
                    val token = input.nextLine()
                    CheckerImpl().checkToken(token)
                }

            }
        }
        2 -> {
            println("Enter the path: ")
            while (true){
                if(input.hasNext()){
                    val path = input.nextLine()
                    CheckerImpl().checkFile(path)
                }

            }
        }
        3 -> exitProcess(0)
    }
}