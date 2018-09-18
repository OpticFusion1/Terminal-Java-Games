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
        Game.whiteScore = Game.size * (Game.size - 1)
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
            place.value = -1
        }

        //Calculate numbers
        Game.allPoints.forEach {
            val place = Game.get(it)
            place.set('_', false, 'w')
            place.empty = true

            if(place.value == -1) {
                place.red = true
                it.adjacent().forEach {
                    val otherPlace = Game.get(it)
                    if(otherPlace.value > -1)
                        otherPlace.value++
                }
            }
        }

        clear()
    }

    override fun clear() {
        Game.allPoints.forEach { Game.get(it).action = Actions.Reveal(it) }
    }

    override fun remove(place: Place?) {

    }

    override fun getSize() = 8
}