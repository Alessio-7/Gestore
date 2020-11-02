package Homework;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.AreaTesto;
import main.Finestra;

public class Giorno {

	private String data;
	private short giorno, mese;
	private Compito[] compiti = null;

	public JPanel getComponent() {

		JPanel ritorno = null;

		if ( this.compiti.length > 0 ) {

			ritorno = new JPanel( new GridLayout( this.compiti.length + 1, 1 ) );

			JTextArea t = new AreaTesto();
			t.setText( "\n\n\tCompiti per il " + this.data );
			t.setBackground( Finestra.coloreContainer );
			t.setForeground( Finestra.coloreSfondo );
			ritorno.add( t );

			for ( int i = 0; i < compiti.length; i++ ) {

				t = new AreaTesto();
				t.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Finestra.coloreContainer ), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ) );
				t.setText( compiti[i].getComponent() );
				t.setEditable( false );

				ritorno.add( t );
			}
		}
		return ritorno;
	}

	public double sommaDifficoltà() {

		double ritorno = 0;
		for ( int i = 0; i < this.compiti.length; i++ ) {
			ritorno += this.compiti[i].getDifficoltà();
		}
		return ritorno;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public short getGiorno() {
		return giorno;
	}

	public void setGiorno( short giorno ) {
		this.giorno = giorno;
	}

	public short getMese() {
		return mese;
	}

	public void setMese( short mese ) {
		this.mese = mese;
	}

	public Compito[] getCompiti() {
		return compiti;
	}

	public void setCompiti( Compito[] compiti ) {
		this.compiti = compiti;
	}

	public Giorno( String data, Compito[] compiti ) {

		this.data = data;
		String[] d = data.split( "/" );
		this.giorno = new Short( d[0] );
		this.mese = new Short( d[1] );
		this.compiti = compiti;
	}

}
