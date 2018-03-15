package gameloader.tictactoe

import gameloader.Game
import gameloader.base.*
import java.util.*

class TicTacToe: Rules {
    override fun getName() = "Tic-Tac-Toe"

    override fun setup() {
        Game.allPoints.forEach {
            Game.allPoints.forEach {
                val place = Game.get(it)

                //Set grid lines
                when {
                    it.x % 2 == 1 && it.y % 2 == 1 -> place.set('+', false, 'b')
                    it.y % 2 == 1 -> place.set('-', false, 'b')
                    it.x % 2 == 1 -> place.set('|', false, 'b')
                    else -> place.action = Select(it)
                }
            }
        }

        Game.lockChanges = false
    }

    override fun clear() {
        
    }

    override fun remove(place: Place?) {

    }

    override fun getSize() = 5

    class Select(private val p: Point) : Action(p, p) {
        private val place: Place = Game.get(p)

        override fun run(): Boolean {
            if(Game.redTurn) {
                place.set('X', true, 'r')
            } else {
                place.set('O', true, 'w')
            }
            
            place.action = None()
            Game.redTurn = !Game.redTurn
            return true
        }
    }
}