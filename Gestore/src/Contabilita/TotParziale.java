package Contabilita;

public class TotParziale {

	private String nome;
	private double soldi;

	public TotParziale(String n, double s) {

		nome = n;
		soldi = s;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public double getSoldi() {

		soldi = Math.round(soldi * 100);
		soldi /= 100;
		return soldi;
	}

	public void setSoldi(double soldi) {

		this.soldi = soldi;
		soldi = Math.round(soldi * 100);
		soldi /= 100;
	}

	public void addSoldi(double soldi) {

		this.soldi += soldi;
		soldi = Math.round(soldi * 100);
		soldi /= 100;
	}

	public void diminuisciSoldi(double soldi) {

		this.soldi -= soldi;
		soldi = Math.round(soldi * 100);
		soldi /= 100;

	}
}
