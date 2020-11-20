package Homework;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

			ritorno = new JPanel( new GridBagLayout() );

			JTextArea t = new AreaTesto();
			t.setText( "\n\n\tCompiti per il " + this.data + "\n\n" );
			t.setBackground( Finestra.coloreContainer );
			t.setForeground( Finestra.coloreSfondo );
			ritorno.add( t, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 0, 0, 0, 0 ), 0, 0 ) );

			for ( int i = 0; i < compiti.length; i++ ) {

				t = new AreaTesto();
				t.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Finestra.coloreContainer ), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ) );
				t.setText( compiti[i].getComponent() );
				t.setEditable( false );
				ritorno.add( t, new GridBagConstraints( 0, i + 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
			}
		}
		return ritorno;
	}

	public double sommaDifficolt�() {

		double ritorno = 0;
		for ( int i = 0; i < this.compiti.length; i++ ) {
			ritorno += this.compiti[i].getDifficolt�();
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
