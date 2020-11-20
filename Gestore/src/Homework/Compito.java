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
			ritorno = "\n" + this.materia + " :\t" + formattaCompito() + "\t\n\n\tSemplice    " + this.ora + "    " + this.data + "\t\n";
		} else if ( this.difficoltà == NORMALE ) {
			ritorno = "\n" + this.materia + " :\t" + formattaCompito() + "\t\n\n\tNormale     " + this.ora + "    " + this.data + "\t\n";
		} else {
			ritorno = "\n" + this.materia + " :\t" + formattaCompito() + "\t\n\n\tDifficile   " + this.ora + "    " + this.data + "\t\n";
		}
		return ritorno;
	}

	private String formattaCompito() {
		String insert = "\n\t";

		StringBuilder builder = new StringBuilder( this.compito.length() + insert.length() * ( this.compito.length() / 25 ) + 1 );

		int index = 0;
		String prefix = "";
		while ( index < this.compito.length() ) {
			// Don't put the insert in the very first iteration.
			// This is easier than appending it *after* each substring
			builder.append( prefix );
			prefix = insert;
			builder.append( this.compito.substring( index, Math.min( index + 25, this.compito.length() ) ) );
			index += 25;
		}
		return builder.toString();
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
