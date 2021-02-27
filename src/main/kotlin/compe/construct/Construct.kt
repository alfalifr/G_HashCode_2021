package compe.construct

import compe.model.Entry
import compe.model.InputData
import compe.model.Street
import compe.util.Util
import sidev.lib.collection.asMutableList
import sidev.lib.console.str
import sidev.lib.number.roundClosest
import kotlin.math.roundToInt

object Construct {
    fun initSol_1(input: InputData): List<Entry> {
        //intersection id to end-connection
        val intersectionConn= Util.getIntersections(input)
            .asMutableList()
            .apply { sortByDescending { it.streets.size } }

        //val carPosition= Util.getCarInitPositions(input)

        val entries= mutableListOf<Entry>()

        for(intersection in intersectionConn){ //in 0 until input.intersection
            val eachGreenDurr= input.simulDur / intersection.streets.size
            if(eachGreenDurr <= 0) continue
            val greenDurr= mutableMapOf<Street, Int>()
            entries += Entry(intersection.id, greenDurr)
            for(street in intersection.streets){
                greenDurr[street]= eachGreenDurr
            }
        }
        return entries
    }
///*
    fun initSol_2(input: InputData): List<Entry> {
    //intersection id to end-connection
        val intersectionConn= Util.getIntersections(input)
            //.asMutableList()
            //.apply { sortByDescending { it.streets.size } }

        //val carPosition= Util.getCarInitPositions(input)

        val entries= mutableListOf<Entry>()

        for(intersection in intersectionConn){ //in 0 until input.intersection
            //val eachGreenDurr= input.simulDur / intersection.streets.size
            val allDurr= input.simulDur

            //if(eachGreenDurr <= 0) continue
            val greenDurr= mutableMapOf<Street, Int>()
            entries += Entry(intersection.id, greenDurr)

            val mutStreets= intersection.streets
                //.asMutableList()
                //.apply { sortByDescending { it.duration } }
            val total= mutStreets.sumBy { it.duration }.toDouble()

            for(street in mutStreets){
                val percentage= street.duration / total
                val eachDurr= allDurr * percentage
                if(eachDurr <= 0) continue //eachDurr= 1.0
                greenDurr[street]= eachDurr.roundToInt()
            }
        }
        return entries
    }

    fun initSol_3(input: InputData): List<Entry> {
        val intersectionConn= Util.getIntersections(input)
            //.asMutableList()
            //.apply { sortByDescending { it.streets.size } }
/*
        /*
        C1= S2, S3
        C2= S1, S3

        S1,     S2,     S3
        1,0,0   1,0,0   0,2,0
        */
        val streetCrowdnessMatrix= Array<MutableMap<Int, Int>>(input.streets.size) {
            mutableMapOf()
        }

        for(c in input.cars){
            for((i, s) in c.streets.withIndex()){
                streetCrowdnessMatrix[s.id][i]=
                    streetCrowdnessMatrix[s.id][i]?.inc() ?: 1
            }
        }
 */
        val streetCrowdness= IntArray(input.streets.size)

        for(c in input.cars){
            for(s in c.streets)
                streetCrowdness[s.id]++
        }

        val entries= mutableListOf<Entry>()

        for(intersection in intersectionConn){ //in 0 until input.intersection
            //val eachGreenDurr= input.simulDur / intersection.streets.size
            val allDurr= input.simulDur

            //if(eachGreenDurr <= 0) continue
            val greenDurr= mutableMapOf<Street, Int>()

            //val mutStreets= intersection.streets
                //.asMutableList()
                //.apply { sortByDescending { it.duration } }
            //val total= mutStreets.sumBy { it.duration }.toDouble()

            var crowdness= 0
            val strItr= intersection.streets.iterator()
            if(!strItr.hasNext()) continue

            var mostCrowdedStr= strItr.next()
            while(strItr.hasNext()){
                val street= strItr.next()
                val crowdnessItr= streetCrowdness[street.id]
                if(crowdness < crowdnessItr){
                    crowdness= crowdnessItr
                    mostCrowdedStr= street
                }
                greenDurr[street]= 1
            }
            greenDurr[mostCrowdedStr]= (allDurr * 10 / input.cars.size).let {
                if(it > 0) it else 1
            } //- intersection.streets.size -1
            //if(crowdness > 0){ }
            if(greenDurr.isNotEmpty())
                entries += Entry(intersection.id, greenDurr)
        }
        return entries
    }

    fun initSol_4(input: InputData): List<Entry> {
        val intersectionConn= Util.getIntersections(input)
            //.asMutableList()
            //.apply { sortByDescending { it.streets.size } }
///*
        /*
        C1= S2, S3
        C2= S1, S3

        S1,     S2,     S3
        1,0,0   1,0,0   0,2,0
        */
        val streetCrowdnessMatrix= Array<MutableMap<Int, Int>>(input.streets.size) {
            mutableMapOf()
        }

        for(c in input.cars){
            for((i, s) in c.streets.withIndex()){
                streetCrowdnessMatrix[s.id][i]=
                    streetCrowdnessMatrix[s.id][i]?.inc() ?: 1
            }
        }
// */
        val streetCrowdness= IntArray(input.streets.size)

        for(c in input.cars){
            for(s in c.streets)
                streetCrowdness[s.id]++
        }

        val entries= mutableListOf<Entry>()

        for(intersection in intersectionConn){ //in 0 until input.intersection
            //val eachGreenDurr= input.simulDur / intersection.streets.size
            val allDurr= input.simulDur

            //if(eachGreenDurr <= 0) continue
            val greenDurr= mutableMapOf<Street, Int>()
            entries += Entry(intersection.id, greenDurr)

            //val mutStreets= intersection.streets
                //.asMutableList()
                //.apply { sortByDescending { it.duration } }
            //val total= mutStreets.sumBy { it.duration }.toDouble()

            var crowdness= 0
            val strItr= intersection.streets.iterator()
            if(!strItr.hasNext()) continue

            var mostCrowdedStr= strItr.next()
            while(strItr.hasNext()){
                val street= strItr.next()
                val crowdnessItr= streetCrowdness[street.id]
                if(crowdness < crowdnessItr){
                    crowdness= crowdnessItr
                    mostCrowdedStr= street
                }
                greenDurr[street]= 1
            }
            greenDurr[mostCrowdedStr]= (allDurr * 10 / crowdness).let {
                if(it > 0) it else 1
            } //- intersection.streets.size -1
        }
        return entries
    }
// */

/*
    fun assignToEntry(input: InputData){
        //intersection id to end-connection
        val intersectionConn= Util.calculateStreetConnected(input)
            .asMutableList()
            .apply { sortByDescending { it.second } }

        val carPosition= Util.getCarInitPositions(input)

    }
 */
}