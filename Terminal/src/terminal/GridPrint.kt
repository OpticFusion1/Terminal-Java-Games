package terminal

import gameloader.*

fun main(args: Array<String>) {
    GameList().set(0)
    Game.setup(8)

    while(Game.inPlay) {
        display()
        println(Game.redTurn)
        input()
    }

}

private fun display() {
    val board = Game.board
    var all = "  "

    for(i in 0 until board.size)
        all += "$i "

    var i = 0
    for(row: Array<Place> in board) {
        all += "\n$i "

        for(place in row) {
            val c = place.type
            all += "$c "
        }

        all += "$i"
        i++
    }

    all += "\n  "
    for(i in 0 until board.size)
        all += "$i "

    println(all)
}

private fun input() {
    val line = readLine()?.split(" ")
    if(line != null) {
        val p = Point(line[0].toInt(), line[1].toInt())
        Game.rules.select(p)
    }
}
