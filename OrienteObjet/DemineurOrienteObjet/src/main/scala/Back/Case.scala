package Back

class Case {
  private var isMine: Boolean = false
  private var isRevealed: Boolean = false
  private var adjacentMines: Int = 0

  def getMine: Boolean = {
    isMine
  }
  def setMine(): Unit = {
    isMine = true
  }

  def getAdjacentMines: Int = {
    adjacentMines
  }

  def setAdjacentMines(xMines: Int): Unit = {
    adjacentMines = xMines
  }

  def getReveal: Boolean = {
    isRevealed
  }

  def reveal(): Unit = {
    isRevealed = true
  }


  override def toString: String = {
    if (isRevealed) {
      if (isMine) "X" else adjacentMines.toString
    } else {
      " "
    }
  }
}
