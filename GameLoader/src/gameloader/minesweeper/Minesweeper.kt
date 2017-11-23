package gameloader.minesweeper

import gameloader.Game
import gameloader.base.Place
import gameloader.base.Point
import gameloader.base.Rules
import java.util.*

class Minesweeper: Rules {
    override fun getName() = "Minesweeper"

    override fun setup() {
        Game.redScore = Game.size
        val rand = Random()

        //Place mines
        for(i in 1..Game.redScore) {
            //Get a new location
            var point : Point
            do {
                point = Point(rand.nextInt(Game.size), rand.nextInt(Game.size))
            } while(Game.get(point).red)

            //Set actual mine
            val place = Game.get(point)
            place.red = true
            place.empty = false
        }

        //Calculate numbers
        Game.allPoints.forEach {
            val place = Game.get(it)

            if(!place.red) {
                it.adjacent().forEach {
                    if(Game.get(it).red) {
                        place.value++
                        place.empty = false
                        place.set('0' + place.value, false, 'l')
                    }
                }
            }

            if(place.empty)
                place.set('.', false, 'w')
        }
    }

    override fun clear() {

    }

    override fun remove(place: Place?) {

    }
}