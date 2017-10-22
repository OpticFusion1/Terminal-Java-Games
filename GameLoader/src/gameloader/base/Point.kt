package gameloader.base

class Point(var x: Int = 0, var y: Int = 0) {

    //Add point to this
    fun add(point: Point) = Point(x + point.x, y + point.y)

    //Write out point cords
    fun print() = "(${'A' + x}, $y)"

    fun inSquare(size: Int) = x in 0 until size && y in 0 until size
}