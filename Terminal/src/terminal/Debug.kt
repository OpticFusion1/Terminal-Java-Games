package terminal

import gameloader.*

fun main(args: Array<String>) {
    GameList().set(0)
    Game.setup(8)

    while(Game.inPlay) {
        debug()
        println(Game.redTurn)
        input()
    }
}

private fun debug() {
    println()
    while(Game.changed.size > 0) {
        val p = Game.changed.poll()
        println(Game.get(p).debug())
    }
}

private fun input() {
    val line = readLine()?.split(" ")
    if(line != null) {
        val p = Point(line[0].toInt(), line[1].toInt())
        Game.rules.select(p)
    }
}

