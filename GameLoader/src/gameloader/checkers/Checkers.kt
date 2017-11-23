package gameloader.checkers

import gameloader.*
import gameloader.base.None
import gameloader.base.Place
import gameloader.base.Point
import gameloader.base.Rules

class Checkers : Rules {

    override fun getName() = "Checkers"

    override fun setup() {
        Game.allPoints.forEach {
            val place = Game.get(it)

            //Set each square
            when {
                (it.x + it.y) % 2 == 0 -> null
                it.y < 3 -> place.set('C', false, 'w')
                it.y > 4 -> place.set('C', true, 'r')
                else -> place.set('_', true, 'b')
            }

            if(!place.empty) place.action = Actions.Select(it)
        }

        Game.redScore = 12
        Game.whiteScore = 12

        Game.lockChanges = false
    }

    override fun clear() {
        Game.lockChanges = true
        val newChanged = ArrayList<Point>()

        //Get changed squares
        Game.changed.forEach {
            val place = Game.get(it)
            if(place.color == 'g') {
                //Reset square to blank
                place.action = None()
                place.remove()
                newChanged.add(place.point)
            }
        }

        Game.changed = newChanged
        Game.lockChanges = false
    }

    override fun remove(place: Place) {
        place.type = '_'
        place.color = 'b'
    }
}