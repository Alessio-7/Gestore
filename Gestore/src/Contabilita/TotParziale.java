package Contabilita;

import java.awt.Color;

public class TotParziale {

	private String nome;
	private double soldi;
	private Color colore;

	public Color getColore() {
		return colore;
	}

	public void setColore( Color colore ) {
		this.colore = colore;
	}

	public TotParziale( String n, double s, Color c ) {

		nome = n;
		soldi = s;
		colore = c;
	}

	public String getNome() {

		return nome;
	}

	public void setNome( String nome ) {

		this.nome = nome;
	}

	public double getSoldi() {

		soldi = Math.round( soldi * 100 );
		soldi /= 100;
		return soldi;
	}

	public void setSoldi( double soldi ) {

		this.soldi = soldi;
		soldi = Math.round( soldi * 100 );
		soldi /= 100;
	}

	public void addSoldi( double soldi ) {

		this.soldi += soldi;
		soldi = Math.round( soldi * 100 );
		soldi /= 100;
	}

	public void diminuisciSoldi( double soldi ) {

		this.soldi -= soldi;
		soldi = Math.round( soldi * 100 );
		soldi /= 100;

	}
}
