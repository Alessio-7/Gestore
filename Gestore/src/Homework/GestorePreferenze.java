package Homework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Bottone;
import main.Etichetta;

public class GestorePreferenze extends JDialog {

	private static final long serialVersionUID = 1L;
	MainHomework m = new MainHomework();
	boolean fineInserimento = false;

	double maxDifficoltà = 2;
	int anticipoCompiti = 0;

	public void disegnaSchermo() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setLocation( ( (int) screenSize.getWidth() / 2 ) - 230, 500 );
	}

	public void operazione() {

		JPanel pannello = new JPanel();
		pannello.setLayout( new BorderLayout() );

		this.setSize( 500, 150 );
		this.setTitle( "Preferenze" );

		JPanel griglia = new JPanel( new GridLayout( 2, 2 ) );
		griglia.setBackground( m.frame.coloreSfondo );
		pannello.add( griglia, BorderLayout.CENTER );

		Etichetta label = new Etichetta( "Difficoltà massima per giornata : " );
		griglia.add( label );
		String[] diff = { "1 semplice", "1 normale", "1 normale e 1 semplice", "1 difficile", "1 difficile e 1 semplice", "1 difficile e 1 normale", "1 difficile, 1 normale e 1 semplice",
				"2 difficili" };
		JComboBox<String> difficoltà = new JComboBox<>( diff );
		difficoltà.setFont( m.font );
		difficoltà.setSelectedIndex( (int) ( ( maxDifficoltà + 1 ) * 0.5 ) );
		griglia.add( difficoltà );

		label = new Etichetta( "Anticipo compiti : " );
		griglia.add( label );
		String[] anticipi = { "Il giorno stesso", "1 giorno prima", "2 giorni prima", "3 giorni prima" };
		JComboBox<String> anticipo = new JComboBox<>( anticipi );
		anticipo.setFont( m.font );
		anticipo.setSelectedIndex( anticipoCompiti );
		griglia.add( anticipo );

		Bottone imposta = new Bottone( "Imposta" );
		pannello.add( imposta, BorderLayout.SOUTH );
		imposta.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				maxDifficoltà = ( difficoltà.getSelectedIndex() + 1 ) * 0.5;
				anticipoCompiti = anticipo.getSelectedIndex();

				fineInserimento = true;
				dispose();
			}
		} );
		this.getContentPane().removeAll();
		this.getContentPane().add( pannello, BorderLayout.CENTER );
	}

	private JPanel[] aggiungiComponente( JPanel[] arr, JPanel p ) {

		JPanel[] clone = new JPanel[arr.length + 1];

		for ( int i = 0; i < arr.length; i++ ) {
			clone[i] = arr[i];
		}
		clone[clone.length - 1] = p;
		return clone;
	}

	private Compito[] aggiungiCompito( Compito[] c, Compito compito ) {
		Compito[] clone = new Compito[c.length + 1];

		for ( int i = 0; i < c.length; i++ ) {
			clone[i] = c[i];
		}
		clone[clone.length - 1] = compito;
		return clone;
	}

	private Compito[] eliminaCompito( Compito[] c, int index ) {

		Compito[] anotherArray = new Compito[c.length - 1];

		for ( int i = 0, k = 0; i < c.length; i++ ) {

			if ( i == index ) {
				continue;
			}

			anotherArray[k++] = c[i];
		}

		return anotherArray;
	}

	private Giorno[] swap( Giorno[] c, int j ) {
		Giorno[] ritorno = c;

		Giorno compito = ritorno[j];
		ritorno[j] = ritorno[j + 1];
		ritorno[j + 1] = compito;

		return ritorno;
	}

	private Giorno[] sortData( Giorno[] c ) {

		Giorno[] giorni = c;

		for ( int i = 0; i < giorni.length; i++ ) {
			boolean flag = false;
			for ( int j = 0; j < giorni.length - 1; j++ ) {

				String[] data = giorni[j].getData().split( "/" );
				short mese = new Short( data[0] );
				short giorno = new Short( data[1] );

				String[] data1 = giorni[j + 1].getData().split( "/" );
				short mese1 = new Short( data1[0] );
				short giorno1 = new Short( data1[1] );

				boolean controlloData = ( mese > mese1 ) || ( mese == mese1 && giorno > giorno1 ) || ( mese == mese1 && giorno == giorno1 );

				if ( controlloData ) {
					giorni = swap( giorni, j );
					flag = true;
				}
			}
			if ( !flag )
				break;
		}

		return giorni;

	}

	private Giorno[] aggiungiGiorno( Giorno[] c, Giorno compito ) {
		Giorno[] clone = new Giorno[c.length + 1];

		for ( int i = 0; i < c.length; i++ ) {
			clone[i] = c[i];
		}
		clone[clone.length - 1] = compito;
		return clone;
	}

	private String getDataMinore( Compito[] compiti ) {

		String ritorno = compiti[0].getData();

		String[] data;
		short mese;
		short giorno;
		String[] data1;
		short mese1;
		short giorno1;

		for ( int i = 1; i < compiti.length; i++ ) {

			data = ritorno.split( "/" );
			giorno = new Short( data[0] );
			mese = new Short( data[1] );

			data1 = compiti[i].getData().split( "/" );
			giorno1 = new Short( data1[0] );
			mese1 = new Short( data1[1] );

			if ( mese > mese1 || ( mese == mese1 && giorno > giorno1 ) ) {
				ritorno = compiti[i].getData();
			}
		}

		return ritorno;
	}

	private Giorno[] organizzaGiorni( Compito[] compiti ) {

		Giorno[] ritorno = new Giorno[0];
		Giorno g;
		Compito[] cGiorno;
		String[] data;
		short mese;
		short giorno;
		String[] data1;
		short mese1;
		short giorno1;

		for ( int i = 0; i < compiti.length && compiti.length != 0; i = compiti.length - 1 ) {

			cGiorno = new Compito[0];
			cGiorno = aggiungiCompito( cGiorno, compiti[i] );
			compiti = eliminaCompito( compiti, i );
			data = cGiorno[0].getData().split( "/" );
			mese = new Short( data[0] );
			giorno = new Short( data[1] );

			for ( int j = 0; j < compiti.length; ) {

				data1 = compiti[j].getData().split( "/" );
				mese1 = new Short( data1[0] );
				giorno1 = new Short( data1[1] );

				if ( giorno1 == giorno && mese1 == mese ) {
					cGiorno = aggiungiCompito( cGiorno, compiti[j] );
					compiti = eliminaCompito( compiti, j );
				} else {
					j++;
				}
			}

			g = new Giorno( cGiorno[0].getData(), cGiorno );
			ritorno = aggiungiGiorno( ritorno, g );
		}

		return ritorno;
	}

	private Giorno[] organizzaDifficoltà( Giorno[] giorni ) {

		Giorno[] ritorno = new Giorno[0];

		for ( int i = 0; i < giorni.length; i++ ) {

			Giorno ref = giorni[i];
			if ( ref.sommaDifficoltà() < maxDifficoltà && i != giorni.length - 1 ) {

				for ( int k = i + 1; k < giorni.length && ref.sommaDifficoltà() < maxDifficoltà; k++ ) {

					Compito[] refCompiti = giorni[k].getCompiti();

					for ( int j = 0; j < refCompiti.length; j++ ) {

						if ( refCompiti[j].getDifficoltà() <= ( maxDifficoltà - ref.sommaDifficoltà() ) ) {

							ref.setCompiti( aggiungiCompito( ref.getCompiti(), refCompiti[j] ) );
							refCompiti = eliminaCompito( refCompiti, j );
						}
					}
					giorni[k].setCompiti( refCompiti );
				}
			}
			ritorno = aggiungiGiorno( ritorno, ref );
		}

		return ritorno;
	}

	private Giorno[] organizzaAnticipo( Giorno[] giorni ) {

		Giorno[] ritorno = giorni;

		if ( anticipoCompiti > 0 ) {
			Calendar c;

			for ( int i = 0; i < ritorno.length; i++ ) {
				c = Calendar.getInstance();

				boolean giornoDataUguale = false;
				if ( ritorno[i].getCompiti().length > 0 ) {
					ritorno[i].setData( getDataMinore( ritorno[i].getCompiti() ) );

					if ( ( ritorno[i].getMese() >= ( c.get( Calendar.MONTH ) + 1 ) && ( ritorno[i].getGiorno() - anticipoCompiti ) >= c.get( Calendar.DAY_OF_MONTH ) )
							&& !giornoDataUguale ) {
						c.set( c.get( Calendar.YEAR ), ritorno[i].getMese(), ritorno[i].getGiorno() );
						c.add( Calendar.DAY_OF_MONTH, -anticipoCompiti );

						ritorno[i].setData( c.get( Calendar.DAY_OF_MONTH ) + "/" + c.get( Calendar.MONTH ) );
					}
				}
			}
		}
		return ritorno;
	}

	public JPanel[] getOrganizzazione( Compito[] compiti ) {

		JPanel[] ritorno = new JPanel[0];
		if ( compiti.length > 0 ) {
			Compito[] c = compiti;
			Giorno[] giorni = organizzaGiorni( c );
			giorni = sortData( giorni );
			giorni = organizzaAnticipo( giorni );
			giorni = organizzaDifficoltà( giorni );

			for ( int i = 0; i < giorni.length; i++ ) {
				if ( giorni[i].getComponent() != null ) {
					ritorno = aggiungiComponente( ritorno, giorni[i].getComponent() );
				}
			}
		}
		return ritorno;
	}

	public GestorePreferenze( JFrame frame ) {

		super( frame, "", true );
		disegnaSchermo();
	}

}
