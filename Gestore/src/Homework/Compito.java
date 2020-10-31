package Homework;

public class Compito {

	private String materia;
	private String compito;
	private String data;
	private String ora;
	private double difficoltà;

	static public final double SEMPLICE = 0.5;
	static public final double NORMALE = 1;
	static public final double DIFFICILE = 2;
	static public final double[] DIFFICOLTA = { SEMPLICE, NORMALE, DIFFICILE };

	public Compito( String materia, String compito, String dataScadenza, String ora, double difficoltà ) {

		this.materia = materia;
		this.compito = compito;
		this.data = dataScadenza;
		this.ora = ora;
		this.difficoltà = difficoltà;
	}

	public String getComponent() {

		String ritorno = null;

		if ( this.difficoltà == SEMPLICE ) {
			ritorno = "\n" + this.materia + " :\t" + this.compito + "\t\t\n\tSemplice    " + this.ora + "    " + this.data + "\n";
		} else if ( this.difficoltà == NORMALE ) {
			ritorno = "\n" + this.materia + " :\t" + this.compito + "\t\t\n\tNormale     " + this.ora + "    " + this.data + "\n";
		} else {
			ritorno = "\n" + this.materia + " :\t" + this.compito + "\t\t\n\tDifficile   " + this.ora + "    " + this.data + "\n";
		}
		return ritorno;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria( String materia ) {
		this.materia = materia;
	}

	public String getCompito() {
		return compito;
	}

	public void setCompito( String compito ) {
		this.compito = compito;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra( String ora ) {
		this.ora = ora;
	}

	public double getDifficoltà() {
		return difficoltà;
	}

	public void setDifficoltà( double difficoltà ) {
		this.difficoltà = difficoltà;
	}

	public static String[] materie() {
		String[] materie = Lettore.leggimaterie();
		return !( materie.length == 1 && materie[0].equals( "" ) ) ? materie : null;
	}

}
