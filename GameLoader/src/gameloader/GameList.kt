package gameloader

import com.sun.xml.internal.fastinfoset.util.StringArray
import gameloader.checkers.Checkers
import gameloader.minesweeper.Minesweeper

class GameList {
    val games: Array<Rules> = arrayOf(Checkers(), Minesweeper())

    fun options(): StringArray {
        val out = StringArray()
        for(game in games) out.add(game.name)
        return out
    }

    fun set(i: Int) {
        Game.rules = games[i]
    }
}