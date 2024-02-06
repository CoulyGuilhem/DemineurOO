package GUI

import Back.{Case, Plateau}
import scalafx.scene.control.Button
import scalafx.scene.layout.GridPane

class ButtonGrid(rows: Int, cols: Int, plateauStart: Plateau, statusLabel: StatusLabel) extends GridPane {
  var plateau: Plateau = plateauStart
  private var isGameOver: Boolean = false

  for (i <- 0 until rows; j <- 0 until cols) {
    val button = createButton(i, j)
    add(button, j, i)
  }

  private def createButton(row: Int, col: Int): Button = {
    val button = new Button {
      onAction = _ => updateButton(row, col)
    }
    customizeButton(button, plateau.getGrid(row)(col))
    button
  }

  private def updateButton(row: Int, col: Int): Unit = {
    if (!isGameOver && plateau != null) {
      plateau.revealCell(row, col)
      updateGrid()

      if (isDefeat) {
        for (i <- 0 until rows; j <- 0 until cols) {
          plateau.getGrid(i)(j).reveal()
        }
        updateGrid()
        handleGameOver("Défaite...")
      } else if (isVictory) {
        handleGameOver("Victoire !")
      }
    }
  }

  private def handleGameOver(message: String): Unit = {
    isGameOver = true
    statusLabel.updateText(message)
  }

  private def customizeButton(button: Button, caseValue: Case): Unit = {
    button.prefWidth = 30
    button.prefHeight = 30
    if (caseValue.getReveal) {
      if (caseValue.getMine) {
        button.text = "M"
        button.style = "-fx-base: #FF0000;"
      } else if (caseValue.getAdjacentMines != 0) {
        button.text = caseValue.getAdjacentMines.toString
        button.style = "-fx-base: #FFFF00;"
      } else {
        button.text = " "
        button.style = "-fx-base: #00FF00;"
      }
    } else {
      button.text = "?"
      button.style = "-fx-base: #FFFFFF;"
    }
  }

  private def updateGrid(): Unit = {
    if (!isGameOver) {
      children.clear()
      for (i <- 0 until rows; j <- 0 until cols) {
        val button = createButton(i, j)
        add(button, j, i)
      }
    }
  }

  private def isDefeat: Boolean = {
    plateau.getGrid.flatten.exists(caseValue => caseValue.getMine && caseValue.getReveal)
  }

  private def isVictory: Boolean = {
    plateau.getGrid.flatten.forall(caseValue => caseValue.getMine || caseValue.getReveal)
  }
}