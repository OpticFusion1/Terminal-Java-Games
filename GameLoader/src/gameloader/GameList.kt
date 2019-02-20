package gameloader

import com.sun.xml.internal.fastinfoset.util.StringArray
import gameloader.base.Rules
import gameloader.checkers.Checkers
import gameloader.minesweeper.Minesweeper
import gameloader.tictactoe.TicTacToe

public class GameList {
    val games: Array<Rules> = arrayOf(Checkers(), Minesweeper(), TicTacToe())

    fun options(): StringArray {
        val out = StringArray()
        games.forEach { out.add(it.name) }
        return out
    }

    fun set(i: Int) {
        Game.rules = games[i]
    }
}