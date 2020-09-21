package Homework;

//import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.AreaTesto;
import main.Bottone;
import main.Finestra;
import main.MainGestore;
import main.Menu;
import main.MenuItem;

public class MainHomework {

	final Finestra frame = new Finestra( "Homework" );

	// public final Color coloreSfondoDark = new Color( 47, 47, 47 );
	// public final Color coloreMenuBarDark = new Color( 66, 67, 70 );
	// public final Color coloreTestoDark = new Color( 255, 255, 255 );
	// public final Color coloreBottoneDark = new Color( 30, 30, 30 );

	public final Font font = new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 );

	GestoreCompiti gCmpti;
	GestorePreferenze gPrf;
	Lettore lettore = new Lettore();

	private boolean finestraOrganizzazione;

	public void creaframe() {

		// Notifica.notifica( " ", " ", Notifica.AVVISO );

		gCmpti = new GestoreCompiti( frame );
		gPrf = new GestorePreferenze( frame );

		Menu menu;
		MenuItem menuItem;
		menu = new Menu( "Finestra" );

		menuItem = new MenuItem( "Compiti" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				finestraOrganizzazione = false;
				finestraCompiti();
			}
		} );

		menuItem = new MenuItem( "Organizzazione" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				finestraOrganizzazione = true;
				finestraOrganizzazione();
			}
		} );
		menuItem = new MenuItem( "Preferenze" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				gPrf.operazione();
				gPrf.setVisible( true );
				if ( gPrf.isDisplayable() ) {
					while ( !gPrf.fineInserimento ) {
					}
				}
				lettore.salvaDati( gCmpti.compiti, gPrf.maxDifficoltà, gPrf.anticipoCompiti );
				if ( finestraOrganizzazione ) {
					finestraOrganizzazione();
				}
			}
		} );
		menu.aggiungiSeparatore();
		menuItem = new MenuItem( "Scelta gestori" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				MainGestore.main( null );
				frame.dispose();
			}
		} );

		frame.setMenuBar( menu );

		gCmpti.compiti = lettore.leggiCompiti();
		try {
			String[] p = lettore.leggiPreferenze();
			gPrf.maxDifficoltà = new Double( p[0] );
			gPrf.anticipoCompiti = new Integer( p[1] );
		} catch ( ArrayIndexOutOfBoundsException e ) {

		}

		new Thread( gCmpti ).start();

		if ( gCmpti.compiti.length > 0 ) {
			finestraOrganizzazione();
		} else {
			finestraCompiti();
		}
		frame.setVisible( true );
	}

	public void finestraCompiti() {

		try {
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		}

		JPanel g;
		AreaTesto t;

		for ( int i = 0; i < gCmpti.compiti.length; i++ ) {

			g = new JPanel( new GridLayout( 2, 1 ) );
			g.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Finestra.coloreContainer ), BorderFactory.createEmptyBorder( 10, 20, 0, 10 ) ) );
			g.setBackground( Finestra.coloreSfondo );
			t = new AreaTesto();
			t.setText( gCmpti.compiti[i].getComponent() );
			t.setEditable( false );
			g.add( t );

			Bottone modifica = new Bottone( "Modifica" );
			modifica.setName( "" + i );
			modifica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					gCmpti.operazione( GestoreCompiti.MODIFICA, new Integer( modifica.getName() ) );
					gCmpti.setVisible( true );
					if ( gCmpti.isDisplayable() ) {
						while ( !gCmpti.fineInserimento ) {
						}
					}

					lettore.salvaDati( gCmpti.compiti, gPrf.maxDifficoltà, gPrf.anticipoCompiti );

					finestraCompiti();
				}
			} );

			Bottone elimina = new Bottone( "Elimina" );
			elimina.setName( "" + i );

			elimina.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					Compito[] anotherArray = new Compito[gCmpti.compiti.length - 1];

					for ( int i = 0, k = 0; i < gCmpti.compiti.length; i++ ) {

						if ( i == new Integer( elimina.getName() ) ) {
							continue;
						}

						anotherArray[k++] = gCmpti.compiti[i];
					}

					gCmpti.compiti = anotherArray;

					lettore.salvaDati( gCmpti.compiti, gPrf.maxDifficoltà, gPrf.anticipoCompiti );

					finestraCompiti();
				}
			} );

			JPanel gl = new JPanel( new WrapLayout() );
			gl.add( modifica );
			gl.add( elimina );
			gl.setBackground( Finestra.coloreSfondo );
			g.add( gl );

			frame.wp.add( g );
		}

		Bottone aggiungi = new Bottone( "+" );
		aggiungi.setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 50 ) );
		aggiungi.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				gCmpti.operazione( GestoreCompiti.AGGIUNGI, 0 );
				gCmpti.setVisible( true );
				if ( gCmpti.isDisplayable() ) {
					while ( !gCmpti.fineInserimento ) {
					}
				}

				lettore.salvaDati( gCmpti.compiti, gPrf.maxDifficoltà, gPrf.anticipoCompiti );

				finestraCompiti();
			}
		} );

		frame.wp.add( aggiungi );

		frame.aggiorna();
	}

	public void finestraOrganizzazione() {
		try {
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		}

		JPanel[] componenti = gPrf.getOrganizzazione( gCmpti.compiti );

		for ( int i = 0; i < componenti.length; i++ ) {
			frame.wp.add( componenti[i] );
		}
		frame.aggiorna();
	}

	public static void main( String[] args ) {

		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				MainHomework interfaccia = new MainHomework();
				interfaccia.creaframe();
			}
		} );
	}
}
