package modele;

import java.util.ArrayList;

public class GestionParametre {
	private boolean clavierActiver;
	private double tailleMotADeviner;
	
	public GestionParametre(boolean clavierActiver, double tailleMotADeviner) {
		this.clavierActiver = clavierActiver;
		this.tailleMotADeviner = tailleMotADeviner;
	}
	
	public boolean getClavierActiver() {
		return this.clavierActiver;
	}
	
	public double getTailleMotADeviner() {
		return this.tailleMotADeviner;
	}
	
	public void setParametre(ArrayList<Double> valeurParametre) {
		if (valeurParametre.get(0) == 1) {
			this.clavierActiver = true;
		}
		else {
			this.clavierActiver = false;
		}
		
		this.tailleMotADeviner = valeurParametre.get(1);
	}
}
