package terminal

import gameloader.Game
import gameloader.GameList
import gameloader.base.*

fun main(args: Array<String>) {
    GameList().set(0)
    Game.setup(8)

    //Main game loop
    while(Game.inPlay) {
        Color.reset()
        display()

        //Player and score
        if(!Game.singlePlayer) print(if(Game.redTurn) Color.red("Red's turn") else Color.white("White's turn"))
        if(Game.redTurn) println(" " + Color.red(Game.redScore.toString()) + '/' + Game.whiteScore)
        else println(" " + Game.whiteScore + '/' + Color.red(Game.redScore.toString()))

        choices()
        input()
    }

    //Show winner
    display()
    if(!Game.singlePlayer) println(if(Game.redTurn) "Red wins" else "White wins")
    else println(if(Game.redTurn) "You lose" else "You win")

}

private fun display() {
	//Set display variables
    val board = Game.board
	val gridColor = Color.GREEN
    var all = Color.WHITE + Game.rules.name

    fun columns() {
		//Write column names
        all += gridColor + "\n  "
        (0 until board.size).forEach { all += "${'A' + it} " }

    }
    columns()

	//Start actual grid
    for((i, row: Array<Place>) in board.withIndex()) {
        all += gridColor + "\n$i "

		//Write cells in row
        for(place in row)
            all += Color.color("${place.type} ", place.color)

        all += gridColor + "$i"
    }

	//Finalize grid
    columns()
    println(all)
}

private fun choices() {
    if(Game.choices.isNotEmpty()) {
        Game.choices.forEach {
            print(it.key)
        }
        println()
    }
}

private fun input() {
	//Try till proper response
    var found = false
    while(!found) {
		//Read entry
        val line = readLine()?.toUpperCase()?.toCharArray()
        var x = -1
        var y = -1

        if(line != null) {
            val s = line.joinToString("","","")
            if(Game.choices.isNotEmpty()) {
                val choices = Game.choices.filter { it.key.toUpperCase().contains(s) }
                if(choices.size == 1) found = Game.select(choices.values.first())
            }

            if(!found) {
                for(i in 0 until line.size) {
                    if(line[i] in 'A'..'Z') x = line[i] - 'A'
                    if(line[i] in '0'..'9') y = line[i] - '0'
                }

                if(x in 0 until Game.size && y in 0 until Game.size)
                    found = Game.select(Point(x, y))
            }
        }
        if(!found) println(Color.red("Error: try again"))
    }
}


