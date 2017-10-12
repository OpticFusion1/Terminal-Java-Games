package gameloader.checkers

import gameloader.Action
import gameloader.Game
import gameloader.Place
import gameloader.Point

internal class Actions {
    class Select(private val p: Point, private val a: Boolean = false) : Action(p, p) {
        private val place: Place = Game.get(p)

        override fun run(): Boolean {
            if(place.red == Game.redTurn) {
                var out = false

                //Move toward white
                if(place.type == 'K' || place.red) {
                    out = check(Point(1, -1)) || out
                    out = check(Point(-1, -1)) || out
                }

                //Move toward red
                if(place.type == 'K' || !place.red) {
                    out = check(Point(1, 1)) || out
                    out = check(Point(-1, 1)) || out
                }
                return out
            }
            return false
        }

        private fun check(dir: Point): Boolean {
            //Get new location
            val mid = p.add(dir)
            var out = false

            if(Game.get(mid).empty) if(!a) {
                //If spot available
                val place = Game.get(mid)
                place.action = Move(p, mid)
                place.color = 'g'
                place.change()
                out = true
            } else if(Game.get(mid).red != place.red) {
                //Check if open to jump
                val end = mid.add(p)
                if(Game.get(end).empty) {
                    val place = Game.get(end)
                    place.action = Jump(p, end, mid)
                    place.color = 'g'
                    place.change()
                    out = true
                }
            }
            return out
        }
    }

    class Move(base: Point, target: Point) : Action(base, target) {
        override fun run(): Boolean {
            Game.get(base).move(target)
            Game.get(target).action = Select(target)
            Actions().end(target)
            return true
        }
    }

    class Jump(base: Point, target: Point, val remove: Point) : Action(base, target) {
        override fun run(): Boolean {
            Game.get(base).move(target)
            Game.get(remove).remove()
            Game.get(target).action = Select(target)
            if(Select(target, true).run()) return false
            Actions().end(target)
            return true
        }
    }

    fun end(point: Point) {
        if(Game.get(point).type == 'C') {
            val red = Game.get(point).red
            if((red && point.y == 0) || (!red && point.y == 7)) Game.get(point).type = 'K'
        }
        Game.redTurn = !Game.redTurn
    }

}