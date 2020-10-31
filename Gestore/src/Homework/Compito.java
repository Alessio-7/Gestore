package Homework;

public class Compito {

	private String materia;
	private String compito;
	private String data;
	private String ora;
	private double difficolt�;

	static public final double SEMPLICE = 0.5;
	static public final double NORMALE = 1;
	static public final double DIFFICILE = 2;
	static public final double[] DIFFICOLTA = { SEMPLICE, NORMALE, DIFFICILE };

	public Compito( String materia, String compito, String dataScadenza, String ora, double difficolt� ) {

		this.materia = materia;
		this.compito = compito;
		this.data = dataScadenza;
		this.ora = ora;
		this.difficolt� = difficolt�;
	}

	public String getComponent() {

		String ritorno = null;

		if ( this.difficolt� == SEMPLICE ) {
			ritorno = "\n" + this.materia + " :\t" + this.compito + "\t\t\n\tSemplice    " + this.ora + "    " + this.data + "\n";
		} else if ( this.difficolt� == NORMALE ) {
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

	public double getDifficolt�() {
		return difficolt�;
	}

	public void setDifficolt�( double difficolt� ) {
		this.difficolt� = difficolt�;
	}

	public static String[] materie() {
		String[] materie = Lettore.leggimaterie();
		return !( materie.length == 1 && materie[0].equals( "" ) ) ? materie : null;
	}

}
