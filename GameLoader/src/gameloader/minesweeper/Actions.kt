package gameloader.minesweeper

import gameloader.Game
import gameloader.base.Action
import gameloader.base.None
import gameloader.base.Place
import gameloader.base.Point

internal class Actions {
    class Reveal(private val p: Point): Action(p, p) {
        override fun run(): Boolean {
            if(Game.get(p).red)
                return false

            Actions().show(Game.get(p))

            if(Game.get(p).empty)
                spread(p)

            return true
        }

        private fun spread(p: Point) {
            p.adjacent().forEach {
                val place = Game.get(it)
                Actions().show(place)
                if(place.empty && place.action is Actions.Reveal)
                    spread(it)
            }

        }
    }

    fun show(place: Place) {
        place.action = None()

        //Reveal place value
        when {
            place.empty -> place.set('.', false, 'w')
            place.red -> place.set('*', true, 'r')
            else -> place.set('0' + place.value, false, 'l')
        }
    }
}