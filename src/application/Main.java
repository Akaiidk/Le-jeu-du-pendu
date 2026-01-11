package application;


import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import controleur.ControleurNavbar;
import controleur.ControleurScene1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.GestionJeu;
import modele.GestionParametre;

public class Main extends Application {
	private GestionJeu jeu;
	private GestionParametre parametre;

	
	@Override
	public void init() throws IOException {
		URL url = this.getClass().getResource("../modele/Dico.txt");
		this.jeu = new GestionJeu(new String(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name())));
		this.parametre = new GestionParametre(true, 18);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			ControleurNavbar controleurNavbar = new ControleurNavbar(this.jeu, primaryStage, this.parametre);
			FXMLLoader loadernavbar = new FXMLLoader(getClass().getResource("../vue/Navbar.fxml"));
			loadernavbar.setController(controleurNavbar);
			GridPane navbar = loadernavbar.load();
			
			ControleurScene1 controleurscene1 = new ControleurScene1(navbar);
			FXMLLoader loaderscene1 = new FXMLLoader(getClass().getResource("../vue/Scene1.fxml"));
			loaderscene1.setController(controleurscene1);
			Scene scene1 = new Scene(loaderscene1.load(), 400, 800);
			
			primaryStage.setScene(scene1);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Le Jeu du Pendu");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
