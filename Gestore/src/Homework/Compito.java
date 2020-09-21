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

	static public final String MATEMATICA = "Matematica";
	static public final String LETTERATURA = "Letteratura";
	static public final String ANTOLOGIA = "Antologia";
	static public final String GEOGRAFIA = "Geografia";
	static public final String SCIENZE = "Scienze";
	static public final String INGLESE = "Inglese";
	static public final String MOTORIA = "Motoria";
	static public final String STORIA = "Storia";
	static public final String TECNICA = "Tecnica";
	static public final String FRANCESE = "Francese";
	static public final String RELIGIONE = "Religione";
	static public final String MUSICA = "Musica";
	static public final String ARTE = "Arte";
	static public final String[] MATERIE = { MATEMATICA, LETTERATURA, ANTOLOGIA, GEOGRAFIA, STORIA, SCIENZE, INGLESE, MUSICA, ARTE, FRANCESE, MOTORIA, TECNICA, RELIGIONE };

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
			ritorno = "\n" + this.materia + ":\t" + this.compito + "\t\t\n\tSemplice    " + this.ora + "    " + this.data + "\n";
		} else if ( this.difficolt� == NORMALE ) {
			ritorno = "\n" + this.materia + ":\t" + this.compito + "\t\t\n\tNormale     " + this.ora + "    " + this.data + "\n";
		} else {
			ritorno = "\n" + this.materia + ":\t" + this.compito + "\t\t\n\tDifficile   " + this.ora + "    " + this.data + "\n";
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

}
