package compe.util

//import sidev.lib.console.prine
import compe.model.*
import sidev.lib.console.prine
import java.io.File
import java.io.PrintWriter
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

    /*
    Format:
    DurationSimulation (D) Intersection (I) Street (S) Car (V) Bonus (F)

     */
    fun readInput(name: String): InputData {
        val lines= readFile(name)
        val config= lines[0]

        val d= config[0].toInt()
        val i= config[1].toInt()
        val s= config[2].toInt()
        val v= config[3].toInt()
        val f= config[4].toInt()

        val streetMap= mutableMapOf<String, Street>()
        val streets= mutableListOf<Street>()
        for(o in 1 .. s){
            val line= lines[o]
            val s_= Street(line[2], o-1, line[0].toInt(), line[1].toInt(), line[3].toInt())
            streets += s_
            streetMap[line[2]]= s_
            //prine("line[2]= ${line[2]} s_= $s_")
        }

        val cars= mutableListOf<Car>()
        for(o in s+1 .. s+v){
            val line= lines[o]
            val streetCount= line[0].toInt()
            val streets_= line.subList(1, streetCount)
            val streetForCar= mutableListOf<Street>()
            for(s_ in streets_){
                //streetForCar += streets.find { it.name == s_ }!!
                //prine(s_)
                streetForCar += streetMap[s_]!!
            }
            cars += Car(o-s, streetForCar)
        }
        return InputData(d, i, streets, cars, f)
    }

    /**
     * Connected to each end-intersection.
     * Pair of intersection id and end-connection count.
     */
    fun calculateStreetConnected(input: InputData): List<Pair<Int, Int>> {
        //val intersectionConnection= mutableListOf<Pair<Int, Int>>()
        val intersectionConnectionMap= mutableMapOf<Int, Int>()
/*
        for(i in 0 until input.intersection)
            intersectionConnection += 0
 */
        for(s in input.streets){
            //intersectionConnection[s.end]++
            intersectionConnectionMap[s.end]= intersectionConnectionMap[s.end]?.inc() ?: 1
        }
        return intersectionConnectionMap.map { (k, v) ->
            k to v
        }
    }
    /**
     * Connected to each end-intersection.
     * Pair of intersection id and end-connection count.
     */
    fun getIntersections(input: InputData): List<EndIntersection> {
        val intersections= mutableListOf<EndIntersection>()
        //val intersectionConnection= mutableListOf<Pair<Int, Int>>()
        val intersectionConnectionMap= mutableMapOf<Int, MutableList<Street>>()
/*
        for(i in 0 until input.intersection)
            intersectionConnection += 0
 */
        for(s in input.streets){
            //intersectionConnection[s.end]++
            (intersectionConnectionMap[s.end]
                ?: mutableListOf<Street>().also { intersectionConnectionMap[s.end]= it })
                .add(s)
        }
        return intersectionConnectionMap.map { (k, v) ->
            EndIntersection(k, v)
        }
    }

    /**
     * Returns list that its index is car's id and its content is street id where the is in.
     */
    fun getCarInitPositions(input: InputData): List<CarPosition> = mutableListOf<CarPosition>().apply {
        for(c in input.cars){
            this += CarPosition(
                c.id,
                c.streets[0].id,
                0
            )
        }
    }

    fun saveSol(entries: List<Entry>, name: String){
        val outputFile= //getResFile(name)
            File("output/2021/$name")
        outputFile.parentFile!!.also { prine(it.absoluteFile) }.mkdirs()
        PrintWriter(outputFile).use {
            it.println(entries.size)
            for(e in entries){
                it.println(e.intersection)
                it.println(e.greenDuration.size)
                for((s, durr) in e.greenDuration){
                    it.println("${s.name} $durr")
                }
            }
        }
    }
/*
    fun evaluate(){

    }
*/
}