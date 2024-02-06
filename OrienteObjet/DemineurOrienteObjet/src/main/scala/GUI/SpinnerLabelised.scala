package GUI

import scalafx.scene.control.{Label, Spinner}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

/**
 * Classe qui genere des Spinner avec un label avant
 * @param labelText texte avant le spinner
 * @param minValue valeur minimale du spinneur
 * @param maxValue valeur maximale du spinneur
 * @param initialValue valeur de base du spinneur
 */
class SpinnerLabelised(labelText: String, minValue: Int, maxValue: Int, initialValue: Int) extends HBox {
  private val label: Label = new Label(labelText) {
    textFill = Color.White
  }
  private val spinner: Spinner[Int] = new Spinner(minValue, maxValue, initialValue)

  children = Seq(label, spinner)

  def getSpinnerValue: Int = {
    spinner.value.value
  }
}
