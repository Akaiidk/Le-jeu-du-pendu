package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import modele.GestionParametre;

public class ControleurParametre {
	private GestionParametre parametre;
	
	@FXML
	private RadioButton _radio;
	
	@FXML
	private Slider _slider;
	
	@FXML
	private Label _resizableText;
	
	public ControleurParametre(GestionParametre parametre) {
		this.parametre = parametre;
	}
	
	@FXML
	private void initialize() {
		_radio.setSelected(this.parametre.getClavierActiver());
		_slider.setValue(this.parametre.getTailleMotADeviner());
		_resizableText.setStyle("-fx-font-size: "+String.valueOf(this.parametre.getTailleMotADeviner())+"px");
	}
	
	@FXML
	private void cliquerSlider(MouseEvent event) throws IOException {
		Slider slider = ((Slider) (event.getSource()));
		_resizableText.setStyle("-fx-font-size: "+String.valueOf(slider.getValue())+"px");
	}
	
	public ArrayList<Double> donnerValeurParametre() {
		ArrayList<Double> valeurParametre = new ArrayList<Double>();
		if (_radio.isSelected()) {
			valeurParametre.add((double) 1);
		}
		else {
			valeurParametre.add((double) 0);
		}
		
		valeurParametre.add(_slider.getValue());
		
		return valeurParametre;
	}
 }
