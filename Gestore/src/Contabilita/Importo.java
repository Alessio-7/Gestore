package Contabilita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Bottone;
import main.CampoTesto;
import main.Etichetta;
import main.Finestra;

public class Importo extends JDialog {

	private static final long serialVersionUID = 1L;

	boolean fineInserimento = false;

	TotParziale[] parziali = new TotParziale[0];

	public static final int AGGIUNGI = 0;
	public static final int ELIMINA = 1;
	public static final int CORREGGI = 2;

	boolean operazioneSvolta = false;
	String nome = "";
	String soldi = "";
	String nome2 = "";
	String soldi2 = "";

	public void disegnaSchermo() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLayout( new BorderLayout() );
		setResizable( false );
		setLocation( (int) ( ( screenSize.getWidth() / 2 ) - 230 ), 500 );

	}

	public void operazione( int tipoBottone1, int id ) {

		JPanel pannello = new JPanel();
		switch (tipoBottone1) {

			case ( AGGIUNGI ): {
				operazioneSvolta = false;

				JPanel griglia = new JPanel( new GridLayout( 3, 2 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 175 );
				this.setTitle( "Aggiungi importo" );

				Etichetta label = new Etichetta( "Nome importo : " );
				griglia.add( label );

				CampoTesto nomeTXT = new CampoTesto( "Importo " + parziali.length );
				griglia.add( nomeTXT );

				label = new Etichetta( "Quantità  soldi : " );
				griglia.add( label );

				CampoTesto soldiTXT = new CampoTesto( "100.0" );
				griglia.add( soldiTXT );

				label = new Etichetta( "Colore : " );
				griglia.add( label );

				JPanel rgb = new JPanel( new GridLayout( 1, 6 ) );
				rgb.setBackground( Finestra.coloreSfondo );
				rgb.add( new Etichetta( "R :" ) );

				CampoTesto rTXT = new CampoTesto();
				rTXT.setText( "204" );
				rgb.add( rTXT );

				rgb.add( new Etichetta( "G :" ) );

				CampoTesto gTXT = new CampoTesto();
				gTXT.setText( "204" );
				rgb.add( gTXT );

				rgb.add( new Etichetta( "B :" ) );

				CampoTesto bTXT = new CampoTesto();
				bTXT.setText( "204" );
				rgb.add( bTXT );

				griglia.add( rgb );

				Bottone aggiungi = new Bottone( "Aggiungi" );
				pannello.add( aggiungi, BorderLayout.SOUTH );
				aggiungi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						if ( !( nomeTXT.getText().equals( "" ) & soldiTXT.getText().equals( "" ) ) ) {
							nome = nomeTXT.getText();
							soldi = " =  " + soldiTXT.getText();

							TotParziale parziale1 = new TotParziale( nomeTXT.getText(), new Double( soldiTXT.getText() ),
									new Color( new Integer( rTXT.getText() ), new Integer( gTXT.getText() ), new Integer( bTXT.getText() ) ) );

							aggiungiArrayParziali( parziale1 );

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

				JPanel griglia = new JPanel( new GridLayout( 3, 2 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 175 );
				this.setTitle( "Modifica importo" );

				Etichetta label = new Etichetta( "Nome importo : " );
				griglia.add( label );
				CampoTesto nomeTXT = new CampoTesto( parziali[id].getNome() );
				griglia.add( nomeTXT );

				label = new Etichetta( "Quantità  soldi : " );
				griglia.add( label );
				CampoTesto soldiTXT = new CampoTesto( "0.0" );
				griglia.add( soldiTXT );

				label = new Etichetta( "Colore : " );
				griglia.add( label );

				JPanel rgb = new JPanel( new GridLayout( 1, 6 ) );
				rgb.setBackground( Finestra.coloreSfondo );
				rgb.add( new Etichetta( "R :" ) );

				CampoTesto rTXT = new CampoTesto();
				rTXT.setText( "" + parziali[id].getColore().getRed() );
				rgb.add( rTXT );

				rgb.add( new Etichetta( "G :" ) );

				CampoTesto gTXT = new CampoTesto();
				gTXT.setText( "" + parziali[id].getColore().getGreen() );
				rgb.add( gTXT );

				rgb.add( new Etichetta( "B :" ) );

				CampoTesto bTXT = new CampoTesto();
				bTXT.setText( "" + parziali[id].getColore().getBlue() );
				rgb.add( bTXT );

				griglia.add( rgb );

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
								parziali[id].setColore( new Color( new Integer( rTXT.getText() ), new Integer( gTXT.getText() ), new Integer( bTXT.getText() ) ) );
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

	public void aggiungiArrayParziali( TotParziale parziale ) {

		TotParziale[] array = parziali;
		parziali = new TotParziale[parziali.length + 1];
		for ( int i = 0; i < ( parziali.length - 1 ); i++ ) {
			parziali[i] = array[i];
		}

		parziali[parziali.length - 1] = parziale;
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

	public Color[] getColoriImporti() {
		Color[] ritorno = new Color[parziali.length];

		for ( int i = 0; i < ritorno.length; i++ ) {
			ritorno[i] = parziali[i].getColore();
		}

		return ritorno;
	}
}
