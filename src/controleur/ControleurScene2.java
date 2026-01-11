package controleur;

import java.io.IOException;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.GestionJeu;
import modele.GestionParametre;

public class ControleurScene2 {
	private GridPane navbar;
	private GestionJeu jeu;
	private Stage stage;
	private GestionParametre parametre;
	
	@FXML
	private Pane _navbarPane;
	
	@FXML
	private Label _numberLife;
	
	@FXML
	private ImageView _imageHanged;
	
	@FXML
	private GridPane _gridWord;
	
	@FXML
	private Label _successStatement;
	
	public ControleurScene2(GestionJeu jeu, Stage stage, GridPane navbarloaded, GestionParametre parametre) {
		this.navbar = navbarloaded;
		this.jeu = jeu;
		this.stage = stage;
		this.parametre = parametre;
	}

	@FXML
	private void initialize() throws IOException {
		_navbarPane.getChildren().add(navbar);
		this.jeu.InitialiserPartie();
		_numberLife.setText(String.valueOf(this.jeu.getNbMaxErreurs()));
		ColumnConstraints cc = new ColumnConstraints();
		cc.setMinWidth(20);
		cc.setHalignment(HPos.CENTER);
		for (int i = 0; i < this.jeu.getMotMystere().length(); i++) {
			Label labelTemp = new Label("_");
			labelTemp.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
			_gridWord.add(labelTemp, i, 1);
			_gridWord.getColumnConstraints().addAll(cc);
		}
		_successStatement.setText("Cliquez sur une lettre !");
	}
	
	@FXML
	private void insererLettre(KeyEvent event) throws IOException {
		if (this.parametre.getClavierActiver()) {	
			if (event.getCode().isLetterKey()) {
				char kchar = event.getCode().getName().charAt(0);
				if (!jeu.getLettresDejaDonnees().contains(String.valueOf(kchar))){
					GridPane kgrid = (GridPane) event.getSource();
					verifierLettreDansJeu((Button) kgrid.getChildren().get((int)(kchar)-65));
				}
			}
		}	
	}
	
	@FXML
	private void cliquerLettre(MouseEvent event) throws IOException {
		verifierLettreDansJeu((Button) (event.getSource()));
	}
	
	private void verifierLettreDansJeu(Button button) throws IOException {
		char bchar = button.getText().charAt(0);
		this.jeu.MemoriserLettreChoisie(bchar);
		Vector<Integer> pos = new Vector<Integer>();
		int occurance = this.jeu.ChercherLettreDansMot(bchar, pos);
		if (occurance == 0) {
			this.jeu.setNbErreurs(this.jeu.getNbErreurs()+1);
			if (this.jeu.MaxErreursDepasse()) {
				this.stage.setScene(loadScene3());
			}
			else {
				_imageHanged.setImage(new Image(getClass().getResourceAsStream("../ressource/img/hanged"+String.valueOf(this.jeu.getNbMaxErreurs()-this.jeu.getNbErreurs())+".png")));
				_numberLife.setText(String.valueOf(this.jeu.getNbMaxErreurs()-this.jeu.getNbErreurs()));
				_successStatement.setText("Oh non ! Votre lettre n'est pas dans le mot.\nIl vous reste encore "+String.valueOf(this.jeu.getNbMaxErreurs()-this.jeu.getNbErreurs())+" vie(s).\nRéessayez une nouvelle lettre!");
				button.getStyleClass().setAll("buttonLetterWrong");
			}
		}
		else {
			if (this.jeu.ToutTrouve()) {
				this.stage.setScene(loadScene3());
			}
			else {
				for (int i = 0; i < pos.size(); i++) {
					Label labelTemp = new Label(String.valueOf(bchar));
					labelTemp.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
					_gridWord.add(labelTemp, pos.get(i), 0);
				}
				_successStatement.setText("Bien joué.\nVous avez trouvé une lettre du mot.\nElle est "+String.valueOf(occurance)+" fois dans le mot.\nVeuillez recliquer sur une nouvelle lettre.");
				button.getStyleClass().setAll("buttonLetterRight");	
			}
		}
		button.setDisable(true);
	}
	
	private Scene loadScene3() throws IOException {
		ControleurNavbar controleurNavbar = new ControleurNavbar(this.jeu, this.stage, this.parametre);
		FXMLLoader loadernavbar = new FXMLLoader(getClass().getResource("../vue/Navbar.fxml"));
		loadernavbar.setController(controleurNavbar);
		GridPane navbar = loadernavbar.load();
		
		ControleurScene3 controleurscene3 = new ControleurScene3(this.jeu, navbar, this.parametre);
		FXMLLoader loaderscene3 = new FXMLLoader(getClass().getResource("../vue/Scene3.fxml"));
		loaderscene3.setController(controleurscene3);
		Scene scene3 = new Scene(loaderscene3.load(), 400, 800);
		return scene3;
	}
}
