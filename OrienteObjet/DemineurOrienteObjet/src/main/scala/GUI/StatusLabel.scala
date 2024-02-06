package GUI

import scalafx.scene.control.Label
import scalafx.scene.paint.Color

/**
 * Classe permettant de creer un label et de le mettre a jour plus tard
 * @param initialText texte initial
 */
class StatusLabel(initialText: String) extends Label(initialText) {
  textFill = Color.White

  def updateText(newText: String): Unit = {
    text = newText
  }
}
