package gameloader

open class Action(protected val base: Point, protected val target: Point) {

    open fun run(): Boolean {
        return true
    }
}

class None(): Action(Point(), Point()) {
    override fun run(): Boolean {
        return true
    }
}