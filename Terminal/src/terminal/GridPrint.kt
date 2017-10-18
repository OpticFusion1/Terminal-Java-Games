package terminal

import gameloader.*

fun main(args: Array<String>) {
    GameList().set(0)
    Game.setup(8)

    while(Game.inPlay) {
        Color.reset()
        display()
        println(if(Game.redTurn) Color.red("Red's turn") else Color.white("White's turn"))
        input()
    }

}

private fun display() {
    val board = Game.board
    var all = "  "

    fun columns() {
        all += Color.BLUE + "\n  "
        for(i in 0 until board.size)
            all += "$i "
        all += Color.CLEAR
    }

    columns()

    var i = 0
    for(row: Array<Place> in board) {
        all += Color.blue("\n$i ")

        for(place in row) {
            val t = place.type
            val c = place.color

            all += Color.color("$t ", c)
        }

        all += Color.blue("$i")
        i++
    }

    columns()
    println(all)


}

private fun input() {
    var found = false
    while(!found) {
        val line = readLine()?.split(" ")
        if(line != null) {
            val p = Point(line[0].toInt(), line[1].toInt())
            found = Game.select(p)
        }
        if(!found) println(Color.red("Error: try again"))
    }
}
