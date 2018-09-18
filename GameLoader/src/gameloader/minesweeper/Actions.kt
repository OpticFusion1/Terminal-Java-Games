package gameloader.minesweeper

import gameloader.Game
import gameloader.base.Action
import gameloader.base.None
import gameloader.base.Place
import gameloader.base.Point

internal class Actions {
    class Reveal(private val p: Point): Action(p, p) {
        override fun run(): Boolean {
            if(Game.get(p).red) {
                Game.inPlay = false
                Game.redTurn = true
                Game.allPoints.forEach {
                    Actions().show(Game.get(it))
                }
            }


            if(Game.get(p).value == 0)
                spread(p)

            Actions().show(Game.get(p))
            return true
        }

        private fun spread(p: Point) {
            Actions().show(Game.get(p))
            p.adjacent().forEach {
                val place = Game.get(it)
                if(place.empty && place.value == 0 && place.action is Actions.Reveal)
                    spread(it)
                else
                    Actions().show(place)
            }

        }
    }

    fun show(place: Place) {
        if(place.empty) {
            place.action = None()
            Game.whiteScore--

            //Reveal place value
            when {
                place.red -> place.set('*', true, 'r')
                place.value == 0 -> place.set('.', false, 'w')
                else -> place.set('0' + place.value, false, 'l')
            }
        }
    }
}