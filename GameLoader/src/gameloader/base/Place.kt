package gameloader.base

import gameloader.Game


class Place(val point: Point, var color: Char) {
    var type = ' '

    var action: Action = None()

    var empty = true
    var red = false

    fun set(type: Char, red: Boolean, color: Char = ' '): Place {
        this.type = type
        this.red = red

        if(color != ' ') this.color = color
        empty = type == ' ' || type == '_'

        change()
        return this
    }

    fun move(next: Point) {
        //Move to new place
        Game.get(next).set(type, red, color)
        remove()
    }

    fun remove(): Place {
        Game.rules.remove(this)

        empty = true
        action = None()
        change()
        return this
    }

    fun change() {
        if(!Game.lockChanges)
            Game.changed.add(point)
    }

    fun debug(): String {
        return "${point.print()}: $type, $red"
    }

}