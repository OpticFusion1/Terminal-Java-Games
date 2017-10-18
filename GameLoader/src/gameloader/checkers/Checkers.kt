package gameloader.checkers

import gameloader.*

class Checkers : Rules {

    override fun getName() = "Checkers"

    override fun setup() {
        for(p in Game.allPoints) {
            var place = Game.get(p)

            //Set each square
            place = when {
                (p.x + p.y) % 2 == 0 -> place
                p.y < 3 -> place.set('C', false, 'w')
                p.y > 4 -> place.set('C', true, 'r')
                else -> place.set('_', true, 'b')
            }

            if(!place.empty) place.action = Actions.Select(p)
        }

        Game.lockChanges = false
    }

    override fun clear() {
        Game.lockChanges = true

        for(p in Game.changed) {
            val place = Game.get(p)
            if(place.color == 'g') {
                //Reset square to blank
                place.action = Actions.Select(p)
                place.remove()
            }
        }

        Game.lockChanges = false
    }

    override fun remove(place: Place) {
        place.type = '_'
        place.color = 'b'
    }
}