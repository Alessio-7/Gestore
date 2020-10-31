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
		return arrotondaSoldi( soldi );
	}

	public void setSoldi( double soldi ) {
		this.soldi = arrotondaSoldi( soldi );
	}

	public void addSoldi( double soldi ) {
		this.soldi = arrotondaSoldi( this.soldi += soldi );
	}

	public void diminuisciSoldi( double soldi ) {
		this.soldi = arrotondaSoldi( soldi -= soldi );
	}

	public double arrotondaSoldi( double soldi ) {
		soldi = Math.round( soldi * 100 );
		soldi /= 100;
		return soldi;
	}
}
