package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.GestionJeu;
import modele.GestionParametre;

public class ControleurNavbar {
	private GestionJeu jeu;
	private Stage stage;
	private GestionParametre parametre;

	public ControleurNavbar(GestionJeu jeu, Stage stage, GestionParametre parametre) {
		this.jeu = jeu;
		this.stage = stage;
		this.parametre = parametre;
	}
	
	@FXML
	private void initialize(){
	}

	@FXML
	private void commencerNouvellePartie() throws IOException {
		ControleurNavbar controleurNavbar = new ControleurNavbar(this.jeu, this.stage, this.parametre);
		FXMLLoader loadernavbar = new FXMLLoader(getClass().getResource("../vue/Navbar.fxml"));
		loadernavbar.setController(controleurNavbar);
		GridPane navbar = loadernavbar.load();
		
		ControleurScene2 controleurscene2 = new ControleurScene2(this.jeu, this.stage, navbar, this.parametre);
		FXMLLoader loaderscene2 = new FXMLLoader(getClass().getResource("../vue/Scene2.fxml"));
		loaderscene2.setController(controleurscene2);
		Scene scene2 = new Scene(loaderscene2.load(), 400, 800);
		
		this.stage.setScene(scene2);
		this.stage.show();
	}
	
	@FXML
	private void ouvrirParametre() throws IOException {
		Dialog<ArrayList<Double>> dialog = new Dialog<>();
		dialog.setTitle("Paramètres");
		
		ControleurParametre controleurParametre = new ControleurParametre(this.parametre);
		FXMLLoader loaderParametre = new FXMLLoader(getClass().getResource("../vue/Parametre.fxml"));
		loaderParametre.setController(controleurParametre);
		dialog.getDialogPane().setContent(loaderParametre.load());
		
		ButtonType buttonApply = new ButtonType("Appliquer les paramètres", ButtonData.OK_DONE);
		ButtonType buttonQuit = new ButtonType("Quitter les paramètres", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonApply, buttonQuit);
		
		dialog.setResultConverter(new Callback<ButtonType, ArrayList<Double>>() {
			@Override
			public ArrayList<Double> call(ButtonType b) {
		        if (b == buttonApply) {
		            return (controleurParametre.donnerValeurParametre());
		        }
		        return null;
		    }
		});
		
		Optional<ArrayList<Double>> result = dialog.showAndWait();
		
		if (result.isPresent()) {
			this.parametre.setParametre(result.get());
		}
	}
	
	@FXML
	private void ouvrirRegle() {
		Alert dialogA = new Alert(AlertType.CONFIRMATION);
		dialogA.setTitle("Règles & Support");
		dialogA.setHeaderText("Règles & Support");
		dialogA.setContentText("BUT DU JEU :\n\n"
				+ "Le jeu du pendu consiste à deviner un mot en proposant des lettres"
				+ ", chaque erreur entraînant l'ajout d'une partie du corps d'un pendu,"
				+ "et le but est de trouver le mot avant que le dessin soit complet.\n\n"
				+ "REGLES DU JEU :\n\n"
				+ "Choix du mot : Le jeu choisit un mot secret aléatoire, et affiche des tirets "
				+ "pour chaque lettre du mot à deviner (par exemple, \"_ _ _ _\" pour un mot de 4 lettres).\n\n"
				+ "Propositions de lettres : Le joueur propose des lettres en cliquant sur des lettres de l'alphabet affichées à l'écran.\n\n"
				+ "Réponse : Si la lettre proposée est dans le mot, elle est révélée à sa place (par exemple, \"C _ _ _\" si le mot est \"CAVE\"). "
				+ "Si la lettre n'est pas dans le mot, une partie du corps du pendu (tête, bras, etc.) est ajoutée à l'écran. "
				+ "Le joueur voit son \"pendu\" se dessiner progressivement à chaque erreur.\n\n"
				+ "Objectif : Le but du joueur est de deviner le mot avant que le dessin du pendu ne soit complet."
				+ "Le jeu peut afficher un message de victoire si le joueur devine correctement, "
				+ "ou un message de défaite si le dessin du pendu est terminé avant que le mot ne soit trouvé.\n\n"
				+ "SUPPORT :\n\n"
				+ "Numéro de téléphone : //Numéro de l'entreprise\n"
				+ "Adresse Mail : //Adresse Mail de l'entreprise"
				);
		dialogA.getButtonTypes().setAll(ButtonType.OK);
		dialogA.showAndWait();
	}
	
	@FXML
	private void quitterJeu() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Quitter le jeu");
		
		FXMLLoader loaderQuitter = new FXMLLoader(getClass().getResource("../vue/Quitter.fxml"));
		GridPane grille = loaderQuitter.load();
		dialog.getDialogPane().setContent(grille);
		
		ButtonType buttonYes = new ButtonType("Oui", ButtonData.OK_DONE);
		ButtonType buttonNo = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonYes, buttonNo);
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.get() == buttonYes) {
			Platform.exit();
		}
	}
	
	@FXML
	private void devoilerBulle(MouseEvent event) {
		Button bclic = (Button) (event.getSource());
		Tooltip toolTip = bclic.getTooltip();
		toolTip.show(bclic, event.getSceneX()+10, event.getSceneY()+10);
	}
	
	@FXML
	private void cacherBulle(MouseEvent event) {
		Button bclic = (Button) (event.getSource());
		Tooltip toolTip = bclic.getTooltip();
		toolTip.hide();
	}
}
