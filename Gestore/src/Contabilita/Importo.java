package Contabilita;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Bottone;
import main.Etichetta;

public class Importo extends JDialog {

	private static final long serialVersionUID = 1L;

	// valore booleano per indicare di aver finito inserimento
	boolean fineInserimento = false;

	TotParziale[] parziali = new TotParziale[0];
	Interfaccia interfaccia = new Interfaccia();

	public static final int AGGIUNGI = 0;
	public static final int ELIMINA = 1;
	public static final int CORREGGI = 2;

	int nOggetti = 6;

	boolean operazioneSvolta = false;
	String nome = "";
	String soldi = "";
	String nome2 = "";
	String soldi2 = "";

	public void disegnaSchermo() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setLocation( ( (int) screenSize.getWidth() / 2 ) - 230, 500 );

	}

	public void operazione( int tipoBottone1, int id ) {

		JPanel pannello = new JPanel();
		switch (tipoBottone1) {

			case ( AGGIUNGI ): {
				operazioneSvolta = false;

				JPanel griglia = new JPanel( new GridLayout( 2, 2 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 200 );
				this.setTitle( "Aggiungi importo" );

				Etichetta label1 = new Etichetta( "Nome importo : " );
				griglia.add( label1 );
				JTextField nomeTXT = new JTextField( "Importo " + parziali.length );
				griglia.add( nomeTXT );
				Etichetta label2 = new Etichetta( "Quantità  soldi : " );
				griglia.add( label2 );
				JTextField soldiTXT = new JTextField( "100.0" );
				griglia.add( soldiTXT );
				Bottone aggiungi = new Bottone( "Aggiungi" );
				pannello.add( aggiungi, BorderLayout.SOUTH );
				aggiungi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						if ( !( nomeTXT.getText().equals( "" ) & soldiTXT.getText().equals( "" ) ) ) {
							nome = nomeTXT.getText();
							soldi = " =  " + soldiTXT.getText();

							TotParziale parziale1 = new TotParziale( nomeTXT.getText(), new Double( soldiTXT.getText() ) );
							aggiungiArrayParziali();

							if ( parziali.length > 0 ) {
								parziali[parziali.length - 1] = parziale1;
							} else {
								parziali[parziali.length - 1] = parziale1;
							}

							operazioneSvolta = true;

							fineInserimento = true;

							dispose();
						}
					}
				} );

				break;

			}
			case ( CORREGGI ): {
				operazioneSvolta = false;
				JPanel griglia = new JPanel( new GridLayout( 2, 2 ) );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 250 );
				this.setTitle( "Modifica importo" );

				Etichetta label1 = new Etichetta( "Nome importo : " );
				griglia.add( label1 );
				JTextField nomeTXT = new JTextField( parziali[id].getNome() );
				griglia.add( nomeTXT );

				label1 = new Etichetta( "Quantità  soldi : " );
				griglia.add( label1 );
				JTextField soldiTXT = new JTextField( "0.0" );
				griglia.add( soldiTXT );

				Bottone correggi = new main.Bottone( "Modifica" );
				pannello.add( correggi, BorderLayout.SOUTH );
				correggi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						if ( !( nomeTXT.getText().equals( "" ) & soldiTXT.getText().equals( "" ) ) ) {
							if ( parziali.length > 0 ) {
								nome = parziali[id].getNome();
								soldi = " =  " + parziali[id].getSoldi();

								parziali[id].setSoldi( new Double( soldiTXT.getText() ) );
								parziali[id].setNome( nomeTXT.getText() );
								operazioneSvolta = true;

								fineInserimento = true;

								nome2 = parziali[id].getNome();
								soldi2 = " =  " + parziali[id].getSoldi();
							}

							dispose();
						}
					}
				} );
				break;
			}
		}
		this.getContentPane().removeAll();
		this.getContentPane().add( pannello, BorderLayout.CENTER );

	}

	public Importo( JFrame frame ) {

		super( frame, "", true );
		disegnaSchermo();

	}

	public Importo() {

	}

	public String generaParziali( int i ) {

		String ritorno = "";
		try {
			ritorno = parziali[i].getNome() + " : \t" + parziali[i].getSoldi() + "€";
		} catch ( NullPointerException e ) {
		}
		return ritorno;
	}

	public void aggiungiArrayParziali() {

		TotParziale[] array = parziali;
		parziali = new TotParziale[parziali.length + 1];
		for ( int i = 0; i < ( parziali.length - 1 ); i++ ) {
			parziali[i] = array[i];
		}
	}

	public double sommaParziali() {

		double somma = 0;

		try {
			for ( int i = 0; i < parziali.length; i++ ) {
				somma += parziali[i].getSoldi();
			}
		} catch ( NullPointerException e ) {
		}

		somma = Math.round( somma * 100 );
		somma /= 100;

		return somma;
	}

	public String[] generaOggetti() {

		int a = 0;

		String[] oggetti = new String[parziali.length + nOggetti];

		oggetti[a] = ( "" );

		a++;
		oggetti[a] = "Importi attuali : ";

		a++;
		oggetti[a] = "";

		for ( int i = 0; i < parziali.length; i++ ) {
			if ( !generaParziali( i ).equals( "" ) ) {
				a++;
				oggetti[a] = "\t" + generaParziali( i );
			}
		}

		a++;
		oggetti[a] = "";

		a++;
		oggetti[a] = "Importo totale :  " + sommaParziali();

		return oggetti;
	}

}
