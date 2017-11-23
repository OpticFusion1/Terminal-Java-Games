package gameloader.base

import gameloader.Game

class Point(var x: Int = 0, var y: Int = 0) {

    //Add point to this
    fun add(point: Point) = Point(x + point.x, y + point.y)

    //Write out point cords
    fun print() = "(${'A' + x}, $y)"

    fun inSquare(size: Int) = x in 0 until size && y in 0 until size
    fun inSquare() = inSquare(Game.size)

    fun adjacent(): List<Point> {
        val steps = arrayOf(
            Point(-1, 1),  Point(0, 1),   Point(1, 1),
            Point(-1, 0),                      Point(1, 0),
            Point(-1, -1), Point(0, -1),  Point(1, -1)
        )

        return steps.map { it.add(this) }.filter { it.inSquare() }
    }
}