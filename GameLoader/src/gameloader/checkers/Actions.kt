package gameloader.checkers

import gameloader.base.Action
import gameloader.Game
import gameloader.base.Place
import gameloader.base.Point

internal class Actions {
    class Select(private val p: Point, private val a: Boolean = false) : Action(p, p) {
        private val place: Place = Game.get(p)

        override fun run(): Boolean {
            if(place.red == Game.redTurn) {
                Game.rules.clear()
                var out = false

                //Move toward white
                if(place.type == 'K' || place.red) {
                    out = check(Point(-1, -1)) || out
                    out = check(Point(1, -1)) || out
                }

                //Move toward red
                if(place.type == 'K' || !place.red) {
                    out = check(Point(-1, 1)) || out
                    out = check(Point(1, 1)) || out
                }
                return out
            }
            return false
        }

        private fun check(dir: Point): Boolean {
            //Get new location
            val mid = p.add(dir)
            var out = false

            fun setMove(point: Point, action: Action) {
                //Show place to move to
                val place = Game.get(point)
                place.action = action
                place.color = 'g'
                place.type = '_'
                place.change()
                out = true

                Game.choices.put("${Game.choices.size + 1}:${place.point.print()} ", place.action)
                //this.place.color = 'g'
            }

            if(Game.get(mid).empty) {
                //Show place to move
                if(!a) setMove(mid, Move(p, mid))
            } else if(Game.get(mid).red != place.red) {
                //Check if open to jump
                val end = mid.add(dir)
                if(Game.get(end).empty) setMove(end, Jump(p, end, mid))
            }
            return out
        }
    }

    class Move(base: Point, target: Point) : Action(base, target) {
        override fun run(): Boolean {
            //Reset color
            //Game.get(base).color = if(Game.get(base).red) 'r' else 'w'

            //Move piece to new location
            Game.get(base).move(target)

            Actions().end(target)
            return true
        }
    }

    class Jump(base: Point, target: Point, private val remove: Point) : Action(base, target) {
        override fun run(): Boolean {
            //Make move
            Game.get(base).move(target)
            Game.get(remove).remove()

            //Check for consecutive jumps
            if(Select(target, true).run()) Game.redTurn = !Game.redTurn

            //Finish move
            Game.changeScore(-1, Game.get(remove).red)
            Actions().end(target)
            return true
        }
    }

    fun end(point: Point) {
        //Just some common finalizing
        val place = Game.get(point)
        if(place.type == 'C') {
            val red = place.red
            if((red && point.y == 0) || (!red && point.y == 7)) place.type = 'K'
        }

        place.action = Actions.Select(point)

        //Next move
        if(Game.redScore == 0 || Game.whiteScore == 0) Game.inPlay = false
        else Game.redTurn = !Game.redTurn
    }

}