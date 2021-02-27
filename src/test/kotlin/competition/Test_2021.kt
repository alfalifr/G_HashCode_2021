package competition

import compe.construct.Construct
import compe.util.Util
import org.junit.jupiter.api.Test
import sidev.lib.console.prin

class Test_2021 {
    @Test
    fun readResTest(){
        prin(Util.readFile("test_input.txt"))
    }

    @Test
    fun anyar(){

    }

    @Test
    fun assignment1(){
        val files= arrayOf(
            "a", "b",
            "c", "d", "e", "f"
        )
        for(file in files){
            //val file= "a"
            val input= Util.readInput("$file.txt")
            val sols= Construct.initSol_1(input)
            Util.saveSol(sols, "$file.out")
        }
    }

    @Test
    fun assignment2(){
        val files= arrayOf(
            "a", "b",
            "c", "d", "e", "f"
        )
        for(file in files){
            //val file= "a"
            val input= Util.readInput("$file.txt")
            val sols= Construct.initSol_2(input)
            Util.saveSol(sols, "2_$file.out")
        }
    }

    @Test
    fun assignment3(){
        val files= arrayOf(
            "a", "b",
            "c", "d", "e", "f"
        )
        for(file in files){
            //val file= "a"
            val input= Util.readInput("$file.txt")
            val sols= Construct.initSol_3(input)
            Util.saveSol(sols, "3_$file.out")
        }
    }
}