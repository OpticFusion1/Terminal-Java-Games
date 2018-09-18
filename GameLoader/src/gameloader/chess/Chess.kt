package gameLoader.chess

import gameloader.Game
import gameloader.base.Place
import gameloader.base.Point
import gameloader.base.Rules
import java.util.*

class Chess: Rules {
	override fun getName() = "Chess"

	override fun setup() {
		Game.allPoints.forEach {
			val place = Game.get(it)
			val red = it.x < 3

			//Set each square
            if(it.x == 1 || it.y == 6)
            	place.set('P', red, 'g')
            else {
            	when {
            		
            	}
            }

		}
	}
}