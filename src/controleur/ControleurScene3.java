package controleur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.GestionJeu;
import modele.GestionParametre;

public class ControleurScene3 {
	private GridPane navbar;
	private GestionJeu jeu;
	private GestionParametre parametre;
	
	@FXML
	private Pane _navbarPane;
	
	@FXML
	private Label _victoryStatement;
	
	@FXML
	private Label _victoryDescription;
	
	@FXML
	private ImageView _imageTop;
	
	@FXML
	private ImageView _imageLeft;
	
	@FXML
	private ImageView _imageRight;
	
	@FXML
	private ImageView _imageBottom;
	
	@FXML
	private Label _retryStatement;
	
	@FXML
	private GridPane _gridWord;
	
	public ControleurScene3(GestionJeu jeu, GridPane navbarloaded, GestionParametre parametre) {
		this.navbar = navbarloaded;
		this.jeu = jeu;
		this.parametre = parametre;
	}
	
	@FXML
	private void initialize() throws IOException {
		_navbarPane.getChildren().add(navbar);
		
		if (this.jeu.ToutTrouve()) {
			_victoryStatement.getStyleClass().add("labelVictory");
			_victoryStatement.setText("VICTOIRE");
			_victoryDescription.setText("Bravo ! Vous avez deviné le mot :");
			_imageTop.setImage(new Image(getClass().getResourceAsStream("../ressource/img/paillette.png")));
			_imageTop.getStyleClass().add("image-flip-vertical");
			_imageBottom.setImage(new Image(getClass().getResourceAsStream("../ressource/img/paillette.png")));
			_imageLeft.setImage(new Image(getClass().getResourceAsStream("../ressource/img/trompette.png")));
			_imageRight.setImage(new Image(getClass().getResourceAsStream("../ressource/img/trompette.png")));
			_retryStatement.setText("Es-tu prêt à relever un nouveau défi?");
			ColumnConstraints cc = new ColumnConstraints();
			cc.setMinWidth(20);
			cc.setHalignment(HPos.CENTER);
			for (int i = 0; i < this.jeu.getMotMystere().length(); i++) {
				Label label = new Label(String.valueOf(this.jeu.getMotMystere().charAt(i)));
				label.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
				_gridWord.add(label, i, 0);
				Label label2 = new Label("_");
				label2.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
				_gridWord.add(label2, i, 1);
				_gridWord.getColumnConstraints().addAll(cc);
			}
		}
		else {
			_victoryStatement.getStyleClass().add("labelDefeat");
			_victoryStatement.setText("DÉFAITE");
			_victoryDescription.setText("Dommage ! Vous avez perdu.\nLe mot à deviner était : ");
			_imageTop.setImage(new Image(getClass().getResourceAsStream("../ressource/img/rain.png")));
			_imageBottom.setImage(new Image(getClass().getResourceAsStream("../ressource/img/rain.png")));
			_imageLeft.setImage(new Image(getClass().getResourceAsStream("../ressource/img/sad.png")));
			_imageRight.setImage(new Image(getClass().getResourceAsStream("../ressource/img/sad.png")));
			_retryStatement.setText("Je suis sûr que tu peux mieux faire, retentes ta\n chance !");
			ColumnConstraints cc = new ColumnConstraints();
			cc.setMinWidth(20);
			cc.setHalignment(HPos.CENTER);
			for (int i = 0; i < this.jeu.getMotMystere().length(); i++) {
				Label labeltemp = new Label(String.valueOf(this.jeu.getMotMystere().charAt(i)));
				labeltemp.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
				if (this.jeu.getLettresDejaDonnees().indexOf(this.jeu.getMotMystere().charAt(i)) == -1) {
					labeltemp.getStyleClass().add("notFoundText");
				}
				_gridWord.add(labeltemp, i, 0);
				Label labeltemp2 = new Label("_");
				labeltemp2.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
				_gridWord.add(labeltemp2, i, 1);
				_gridWord.getColumnConstraints().addAll(cc);
			}
		}
	}
}
