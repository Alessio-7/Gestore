package Homework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Bottone;
import main.CampoTesto;
import main.ComboBox;
import main.Etichetta;
import main.Finestra;

public class GestoreCompiti extends JDialog implements Runnable {

	private static final long serialVersionUID = 1L;

	Compito[] compiti = new Compito[0];

	boolean fineInserimento = false;

	public static final int AGGIUNGI = 0;
	public static final int ELIMINA = 1;
	public static final int MODIFICA = 2;

	private void disegnaSchermo() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setLocation( ( (int) screenSize.getWidth() / 2 ) - 230, 500 );

	}

	@SuppressWarnings("deprecation")
	public void operazione( int tipoBottone1, int index ) {

		JPanel pannello = new JPanel();
		switch (tipoBottone1) {

			case ( AGGIUNGI ): {
				Date date = new Date();

				JPanel griglia = new JPanel( new GridLayout( 5, 4 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 225 );
				this.setTitle( "Aggiungi compito" );

				Etichetta label = new Etichetta( "Materia compito : " );
				griglia.add( label );
				ComboBox materia = new ComboBox( Compito.materie() );
				griglia.add( materia );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				label = new Etichetta( "Compito : " );
				griglia.add( label );
				CampoTesto compitoTXT = new CampoTesto( "Compito da fare" );
				griglia.add( compitoTXT );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				label = new Etichetta( "Data : " );
				griglia.add( label );
				Integer[] giorni = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
				ComboBox giorno = new ComboBox( giorni );
				try {
					giorno.setSelectedIndex( date.getDate() );
				} catch ( IllegalArgumentException e ) {
					giorno.setSelectedIndex( 30 );
				}
				Integer[] mesi = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
				ComboBox mese = new ComboBox( mesi );
				mese.setSelectedIndex( date.getMonth() );
				griglia.add( giorno );

				label = new Etichetta( " / " );
				griglia.add( label );
				griglia.add( mese );

				label = new Etichetta( "Ora : " );
				String[] ore = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
				ComboBox ora = new ComboBox( ore );
				String[] minuti = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59" };
				ComboBox minuto = new ComboBox( minuti );
				griglia.add( label );
				griglia.add( ora );
				label = new Etichetta( " : " );
				griglia.add( label );
				griglia.add( minuto );

				label = new Etichetta( "Difficoltà : " );
				griglia.add( label );
				String[] d = { "Semplice", "Normale", "Difficile" };
				ComboBox difficoltà = new ComboBox( d );
				griglia.add( difficoltà );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				Bottone aggiungi = new Bottone( "Aggiungi" );
				pannello.add( aggiungi, BorderLayout.SOUTH );
				aggiungi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						Compito compito = new Compito( materia.getSelectedItem().toString(), compitoTXT.getText(), giorno.getSelectedItem() + "/" + mese.getSelectedItem(),
								ora.getSelectedItem() + ":" + minuto.getSelectedItem(), new Double( Compito.DIFFICOLTA[difficoltà.getSelectedIndex()] ) );
						aggiungiCompito( compito );

						fineInserimento = true;
						dispose();
					}
				} );

				break;

			}
			case ( MODIFICA ): {

				JPanel griglia = new JPanel( new GridLayout( 5, 4 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 225 );
				this.setTitle( "Modifica compito" );

				Etichetta label;

				label = new Etichetta( "Materia compito : " );
				griglia.add( label );
				ComboBox materia = new ComboBox( Compito.materie() );
				griglia.add( materia );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				label = new Etichetta( "Compito : " );
				griglia.add( label );
				CampoTesto compitoTXT = new CampoTesto( "Compito da fare" );
				griglia.add( compitoTXT );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				label = new Etichetta( "Data : " );
				griglia.add( label );
				Integer[] giorni = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
				ComboBox giorno = new ComboBox( giorni );
				Integer[] mesi = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
				ComboBox mese = new ComboBox( mesi );
				griglia.add( giorno );
				label = new Etichetta( " / " );
				griglia.add( label );
				griglia.add( mese );

				label = new Etichetta( "Ora : " );
				String[] ore = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
				ComboBox ora = new ComboBox( ore );
				String[] minuti = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59" };
				ComboBox minuto = new ComboBox( minuti );
				griglia.add( label );
				griglia.add( ora );
				label = new Etichetta( " : " );
				griglia.add( label );
				griglia.add( minuto );

				label = new Etichetta( "Difficoltà : " );
				griglia.add( label );
				String[] d = { "Semplice", "Normale", "Difficile" };
				ComboBox difficoltà = new ComboBox( d );
				griglia.add( difficoltà );
				griglia.add( new Etichetta( "" ) );
				griglia.add( new Etichetta( "" ) );

				Bottone modifica = new Bottone( "Modifica" );
				pannello.add( modifica, BorderLayout.SOUTH );
				modifica.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						compiti[index] = new Compito( materia.getSelectedItem().toString(), compitoTXT.getText(), giorno.getSelectedItem() + "/" + mese.getSelectedItem(),
								ora.getSelectedItem() + ":" + minuto.getSelectedItem(), new Double( Compito.DIFFICOLTA[difficoltà.getSelectedIndex()] ) );

						fineInserimento = true;
						dispose();
					}
				} );

				break;
			}
		}
		this.getContentPane().removeAll();
		this.getContentPane().add( pannello, BorderLayout.CENTER );
	}

	public void aggiungiCompito( Compito compito ) {
		Compito[] clone = compiti;
		compiti = new Compito[clone.length + 1];

		for ( int i = 0; i < clone.length; i++ ) {
			compiti[i] = clone[i];
		}
		compiti[compiti.length - 1] = compito;
	}

	public Integer[] getIDs() {

		Integer[] ritorno = new Integer[compiti.length];
		for ( Integer i = 0; i < ritorno.length; i++ ) {
			ritorno[i] = i;
		}
		return ritorno;
	}

	public void eliminaCompito( int index ) {

		Compito[] anotherArray = new Compito[compiti.length - 1];

		for ( int i = 0, k = 0; i < compiti.length; i++ ) {

			if ( i == index ) {
				continue;
			}

			anotherArray[k++] = compiti[i];
		}

		compiti = anotherArray;
	}

	public GestoreCompiti( JFrame frame ) {

		super( frame, "", true );
		disegnaSchermo();

	}

	public GestoreCompiti() {

	}

	@Override
	public void run() {
		Calendar c = Calendar.getInstance();

		int giorno = c.get( Calendar.DAY_OF_MONTH );
		int mese = ( c.get( Calendar.MONTH ) + 1 );
		/*
		 * int ora = c.get( Calendar.HOUR ); int minuto = c.get( Calendar.MINUTE );
		 */

		for ( int i = 0; i < compiti.length; i++ ) {

			String[] data = compiti[i].getData().split( "/" );
			int giornoC = new Integer( data[0] );
			int meseC = new Integer( data[1] );

			/*
			 * String[] orario = compiti[i].getOra().split( ":" ); int oraC = new Integer(
			 * orario[0] ); int minutoC = new Integer( orario[1] );
			 */

			if ( giornoC == giorno && mese == meseC ) {
				Notifica.notifica( "Compito in scadenza", compiti[i].getMateria() + ": " + compiti[i].getCompito(), Notifica.AVVISO );

				/*
				 * while ( true ) { c = Calendar.getInstance();
				 * 
				 * ora = c.get( Calendar.HOUR_OF_DAY ); minuto = c.get( Calendar.MINUTE );
				 * 
				 * int a = -1;
				 * 
				 * for ( int j = 0; j < compiti.length; j++ ) {
				 * 
				 * data = compiti[i].getData().split( "/" ); giornoC = new Integer( data[0] );
				 * meseC = new Integer( data[1] );
				 * 
				 * orario = compiti[i].getOra().split( ":" ); oraC = new Integer( orario[0] );
				 * minutoC = new Integer( orario[1] );
				 * 
				 * if ( ora == oraC && minuto == minutoC && a != j ) { a = j; Notifica.notifica(
				 * "Scadenza compito", compiti[j].getCompito(), Notifica.ERRORE ); } } }
				 */
			}
		}
	}
}
