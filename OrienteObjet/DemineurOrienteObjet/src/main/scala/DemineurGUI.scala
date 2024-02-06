import GUI.{ButtonGrid, SpinnerLabelised, StatusLabel}
import Back.Plateau
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.layout.VBox
import scalafx.scene.control.Button

object DemineurGUI extends JFXApp3 {
  var rows = 10
  var cols = 10
  private var mines = 10
  private var plateau: Plateau = _
  private var buttonGrid: ButtonGrid = _

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Démineur"
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = createVBox()
      }
    }
  }

  private def createVBox(): VBox = {
    val rowsSpinner = new SpinnerLabelised("Rows:", 10, 20, rows)
    val colsSpinner = new SpinnerLabelised("Cols:", 10, 20, cols)
    val minesSpinner = new SpinnerLabelised("Mines:", 10, 50, mines)
    val statusLabel = new StatusLabel("En cours")
    plateau = new Plateau(rowsSpinner.getSpinnerValue, colsSpinner.getSpinnerValue, minesSpinner.getSpinnerValue)
    buttonGrid = new ButtonGrid(rows, cols, plateau, statusLabel)

    val startButton = new Button("Start Game") {
      onAction = _ => {
        println("TEST")
        rows = rowsSpinner.getSpinnerValue
        cols = colsSpinner.getSpinnerValue
        mines =  minesSpinner.getSpinnerValue
        stage.scene = new Scene {
          fill = Color.rgb(38, 38, 38)
          content = createVBox()
        }
      }
    }

    new VBox {
      children = Seq(rowsSpinner, colsSpinner, minesSpinner, statusLabel, startButton, buttonGrid)
    }
  }
}
