package gameloader.checkers

import gameloader.Game
import gameloader.Point
import gameloader.Rules

class Checkers : Rules {

    override fun getName() = "Checkers"

    override fun setup() {
        for(p in Game.allPoints) {
            val place = Game.get(p)

            //Set each square
            Game.board[p.x][p.y] = when {
                (p.x + p.y) % 2 == 0 -> place.set(' ', false, 'w')
                p.y < 3 -> place.set('C', false, 'b')
                p.y > 4 -> place.set('C', true, 'b')
                else -> place.set(' ', true, 'b')
            }

            if(!place.empty) place.action = Actions.Select(p)
        }
    }

    override fun select(point: Point?) {

    }
}