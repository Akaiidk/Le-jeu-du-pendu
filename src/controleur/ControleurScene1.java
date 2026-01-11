package controleur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ControleurScene1 {
	private GridPane navbar;
	
	@FXML
	private Pane _navbarPane;
	
	public ControleurScene1(GridPane navbarloaded) {
		this.navbar = navbarloaded;
	}

	@FXML
	private void initialize() throws IOException {
		_navbarPane.getChildren().add(navbar);
	}
}
