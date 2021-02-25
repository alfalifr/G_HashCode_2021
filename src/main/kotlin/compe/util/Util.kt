package compe.util

//import sidev.lib.console.prine
import java.io.File
import java.util.*

object Util {
    fun getResFile(name: String): File = File(
        javaClass.classLoader.getResource(Config.DATASET_DIR + "/$name")!!.file
    )

    fun readFile(name: String): List<List<String>> {
        val file = getResFile(name)
        //prine(Config.DATASET_DIR + "\\$name")
        val scanner = Scanner(file)
        val list= mutableListOf<List<String>>()
        while(scanner.hasNextLine())
            list += scanner.nextLine().trim().split(" ")
        return list
    }
}