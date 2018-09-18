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

    class Select(p: Point) : Action(p, p) {
        private val place: Place = Game.get(p)

        override fun run(): Boolean {
            if(Game.redTurn) {
                place.set('X', true, 'r')
                Game.redScore++
            } else {
                place.set('O', true, 'w')
                Game.whiteScore++
            }
            
            place.action = None()
            checkWin()
            if(Game.inPlay)
                Game.redTurn = !Game.redTurn
            return true
        }

        private fun inRow(row: Array<Place>) = (row.all { it.type == 'O' } || row.all { it.type == 'X' })
                //.also { row.forEach { print(it.debug()) }; println() }

        private fun checkWin() {
            for(i in 0..4 step 2) {
                if(inRow(arrayOf(Game.board[i][0], Game.board[i][2], Game.board[i][4]))
                                || inRow(arrayOf(Game.board[0][i], Game.board[2][i], Game.board[4][i])))
                    Game.inPlay = false
            }

            if(inRow(arrayOf(Game.board[0][0], Game.board[2][2], Game.board[4][4])) ||
                    inRow(arrayOf(Game.board[0][4], Game.board[2][2], Game.board[4][0])))
                Game.inPlay = false
        }
    }
}