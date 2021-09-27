package dad.AdivinApp;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label saludoLabel;
	private Button saludarButton;
	private TextField nombreText;
	private VBox root;
	private Alert alertInfo, alertWarning, alertError;
	private int numero;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Generamos n�mero aleatorio.
		Random rd = new Random();
		numero = rd.nextInt(100) + 1;

		// Creamos cuadro de texto
		nombreText = new TextField();
		nombreText.setPromptText("0");
		nombreText.setAlignment(Pos.CENTER);
		nombreText.setMaxWidth(100); // texto

		// creamos etiqueta
		saludoLabel = new Label();
		saludoLabel.setText("Introduce un n�mero del 1 al 100.");

		// creamos bot�n
		saludarButton = new Button();
		saludarButton.setText("Comprobar");
		saludarButton.setOnAction(e -> onAdivinar(e));
		saludarButton.setDefaultButton(true);

		// Panel con disposici�n vertical
		root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(saludoLabel, nombreText, saludarButton);

		// Creamos la escena
		Scene scene = new Scene(root, 320, 200);

		// configuramos la ventana
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onAdivinar(ActionEvent e) {
		String cadena = nombreText.getText();
		int numPrueba;

		try {
			numPrueba = Integer.parseInt(cadena);

			if (numPrueba <= 0 || numPrueba > 100) {
				alertError = new Alert(AlertType.ERROR);
				alertError.setTitle("AdivinApp");
				alertError.setHeaderText("ERROR");
				alertError.setContentText("El n�mero introducido no es v�lido, debe estar entre 1 y 100.");
				alertError.showAndWait();
				nombreText.clear();
			} else {

				if (numPrueba < numero) {
					alertWarning = new Alert(AlertType.WARNING);
					alertWarning.setTitle("AdivinApp");
					alertWarning.setHeaderText("�Has fallado!");
					alertWarning.setContentText(
							"El n�mero a adivinar es mayor que " + numPrueba + "." + "\n" + "Vuelve a intentarlo.");
					alertWarning.showAndWait();
					nombreText.clear();
				}

				if (numPrueba == numero) {
					alertInfo = new Alert(AlertType.INFORMATION);
					alertInfo.setTitle("AdiviApp");
					alertInfo.setHeaderText("�Has ganado!");
					alertInfo.setContentText("Solo has necesitado x intentos!");
					alertInfo.setContentText("Vuelve a jugar y hazlo mejor!");
					Random rd = new Random();
					numero = rd.nextInt(100) + 1;

					alertInfo.showAndWait();
					nombreText.clear();
				}

				if (numPrueba > numero) {
					alertWarning = new Alert(AlertType.WARNING);
					alertWarning.setTitle("AdivinApp");
					alertWarning.setHeaderText("�Has fallado!");
					alertWarning.setContentText(
							"El n�mero a adivinar es menor que " + numPrueba + "." + "\n" + "Vuelve a intentarlo.");
					alertWarning.showAndWait();
					nombreText.clear();
				}
			}
		} catch (NumberFormatException e1) {
			alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("AdivinApp");
			alertError.setHeaderText("ERROR");
			alertError
					.setContentText("Ha introducido letras, car�cteres inv�lidos o ha dejado el campo de texto vac�o.");
			alertError.showAndWait();
			nombreText.clear();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
