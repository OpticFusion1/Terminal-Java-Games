package gameloader


class Place(val point: Point, var color: Char) {
    var type = ' '

    var action: Action = None()

    var empty = true
    var red = false

    fun set(type: Char, red: Boolean, color: Char = ' '): Place {
        this.type = type
        this.red = red

        if(color != ' ') this.color = color
        empty = type == ' '

        change()
        return this
    }

    fun move(next: Point) {
        //Move to new place
        Game.get(next).set(type, red)
        remove()
    }

    fun remove() {
        type = ' '
        empty = true
        empty = true
        action = None()
        change()
    }

    fun change() {
        Game.changed.add(point)
    }

    fun debug() = "$point: $type, $red"

}
