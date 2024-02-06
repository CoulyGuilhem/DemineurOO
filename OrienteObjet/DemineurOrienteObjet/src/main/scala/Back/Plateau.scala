package Back

import scala.util.Random

class Plateau(val rows: Int, val cols: Int, var numMines: Int) {

  private val random = new Random()
  private val grid: Array[Array[Case]] = Array.ofDim[Case](rows, cols)
  for (i <- 0 until rows; j <- 0 until cols) {
    grid(i)(j) = new Case()
  }
  placeMines()
  calculateAdjacentMines()

  def getGrid: Array[Array[Case]] = {
    grid
  }

  /**
   * Devoile les cases de maniere recursive si il s'agit d'une case cide
   * @param row ligne
   * @param col colonne
   */
  def revealCell(row: Int, col: Int): Unit = {
    if (row >= 0 && row < rows && col >= 0 && col < cols && !grid(row)(col).getReveal) {
      grid(row)(col).reveal()
      if (grid(row)(col).getAdjacentMines == 0) {
        for (i <- -1 to 1; j <- -1 to 1) {
          revealCell(row + i, col + j)
        }
      }
    }
  }

  /**
   * Place les mines sur le tableau
   */
  private def placeMines(): Unit = {
    for (_ <- 1 to numMines) {
      var minePlaced = false
      while (!minePlaced) {
        val row = random.nextInt(rows)
        val col = random.nextInt(cols)
        if (!grid(row)(col).getMine) {
          grid(row)(col).setMine()
          minePlaced = true
        }
      }
    }
  }

  /**
   * Met a jour la valeur des cases lorsqu'une mine se situe a coté de la case
   */

  private def calculateAdjacentMines(): Unit = {
    for (i <- 0 until rows; j <- 0 until cols) {
      for (x <- -1 to 1; y <- -1 to 1) {
        val newRow = i + x
        val newCol = j + y
        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid(newRow)(newCol).getMine) {
          grid(i)(j).setAdjacentMines(grid(i)(j).getAdjacentMines + 1)
        }
      }
    }
  }
}
