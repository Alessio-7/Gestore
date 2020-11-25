package Contabilita;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.AreaTesto;
import main.Bottone;
import main.CampoTesto;
import main.ComboBox;
import main.Etichetta;
import main.Finestra;
import main.MainGestore;
import main.Menu;
import main.MenuItem;
import main.WrapLayout;

public class Interfaccia {

	final Finestra frame = new Finestra( "Gestore contabilità" );

	JPanel griglia = null;
	JPanel ly = new JPanel( new WrapLayout() );

	Importo importo = null;
	Lettore lettore = new Lettore();

	String[] movimenti = new String[0];

	public final Border border = BorderFactory.createLineBorder( Finestra.coloreContainer );

	public void creaframe() {

		importo = new Importo( frame );

		ly.setBackground( Finestra.coloreMenu );

		frame.add( ly, BorderLayout.SOUTH );

		Menu menu, submenu;
		MenuItem menuItem;
		menu = new Menu( "Funzioni" );

		menuItem = new MenuItem( "HOME" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				home();
			}
		} );

		menuItem = new MenuItem( "Gestione importi" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				try {
					frame.remove( griglia );
				} catch ( NullPointerException e ) {
				}
				gestioneImporti();
			}
		} );

		submenu = new Menu( "Transazione" );
		menuItem = new MenuItem( "IN" );
		submenu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				try {
					frame.remove( griglia );
				} catch ( NullPointerException e ) {
				}
				transazioneIn();
			}
		} );

		menuItem = new MenuItem( "OUT" );
		submenu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				try {
					frame.remove( griglia );
				} catch ( NullPointerException e ) {
				}
				transazioneOut();
			}
		} );

		menu.add( submenu );
		menuItem = new MenuItem( "Trasferimento soldi" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				try {
					frame.remove( griglia );
				} catch ( NullPointerException e ) {
				}
				trasferimentoSoldi();
			}
		} );

		menuItem = new MenuItem( "Mostra movimenti" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				try {
					frame.remove( griglia );
				} catch ( NullPointerException e ) {
				}
				MostraMovimenti();
			}
		} );

		menu.aggiungiSeparatore();

		menuItem = new MenuItem( "Scelta gestore" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				MainGestore.finestraGestore();
				frame.dispose();
			}
		} );

		frame.setMenuBar( menu );

		try {

			importo.parziali = lettore.leggiDatiParziali();

			movimenti = lettore.leggiDatiMovimenti();

			lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );

		} catch ( IOException | NullPointerException e ) {
			e.printStackTrace();
		}

		frame.setVisible( true );

		if ( importo.parziali.length > 0 ) {
			home();
		} else {
			gestioneImporti();
		}
	}

	public int cercaIndex( TotParziale[] array, String s ) {

		int index = 0;

		for ( int i = 0; i < array.length; i++ ) {
			if ( array[i].getNome().equals( s ) ) {
				index = i;
				break;
			}
		}

		return index;
	}

	public void home() {

		try {
			ly.removeAll();
			ly.setVisible( false );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new WrapLayout() );

		AreaTesto t = new AreaTesto();

		t.setText( "Somma totale :\t" + importo.sommaParziali() + "€" );
		t.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 50, 50, 50, 50 ) ) );
		t.setEditable( false );
		frame.wp.add( t );

		for ( int i = 0; i < importo.parziali.length; i++ ) {

			t = new AreaTesto();
			t.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( importo.parziali[i].getColore(), 2 ), BorderFactory.createEmptyBorder( 50, 50, 50, 50 ) ) );
			t.setText( importo.generaParziali( i ) );
			t.setEditable( false );

			frame.wp.add( t );
		}

		griglia = new JPanel( new GridLayout( 5, 1 ) );
		griglia.setBorder( BorderFactory.createEmptyBorder() );
		griglia.setBackground( Finestra.coloreSfondo );
		Bottone bottone;

		bottone = new Bottone( "Gestione importi" );
		bottone.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				frame.remove( griglia );
				gestioneImporti();
			}
		} );
		griglia.add( bottone );

		bottone = new Bottone( "Transazione IN" );
		bottone.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				frame.remove( griglia );
				transazioneIn();
			}
		} );
		griglia.add( bottone );

		bottone = new Bottone( "Transazione OUT" );
		bottone.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				frame.remove( griglia );
				transazioneOut();
			}
		} );
		griglia.add( bottone );

		bottone = new Bottone( "Trasferimento soldi" );
		bottone.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				frame.remove( griglia );
				trasferimentoSoldi();
			}
		} );

		griglia.add( bottone );
		bottone = new Bottone( "Mostra movimenti" );
		bottone.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				frame.remove( griglia );
				MostraMovimenti();
			}
		} );
		griglia.add( bottone );

		frame.add( griglia, BorderLayout.EAST );
		frame.aggiorna();
	}

	public void gestioneImporti() {

		try {
			ly.removeAll();
			ly.setVisible( false );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new WrapLayout() );

		JPanel g;
		AreaTesto t = new AreaTesto();

		t.setText( "Somma totale :\t" + importo.sommaParziali() + "€" );
		t.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 50, 50, 50, 50 ) ) );
		t.setEditable( false );
		frame.wp.add( t );

		for ( int i = 0; i < importo.parziali.length; i++ ) {

			g = new JPanel( new GridLayout( 2, 1 ) );
			Border bordoColorato = BorderFactory.createLineBorder( importo.parziali[i].getColore(), 2 );
			g.setBorder( BorderFactory.createCompoundBorder( bordoColorato, BorderFactory.createEmptyBorder( 50, 50, 25, 50 ) ) );
			g.setBackground( Finestra.coloreSfondo );
			t = new AreaTesto();
			t.setText( importo.generaParziali( i ) + "\n\n" );
			t.setEditable( false );
			g.add( t );

			Bottone modifica = new Bottone( "Modifica" );
			modifica.setName( "" + i );

			modifica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					importo.operazione( Importo.CORREGGI, new Integer( modifica.getName() ) );
					importo.setVisible( true );
					if ( importo.isDisplayable() ) {
						while ( !importo.fineInserimento ) {
						}
					}
					if ( importo.operazioneSvolta == true ) {
						aggiungiMovimenti( "Modificato un importo » " + importo.nome + importo.soldi + "€ -> " + importo.nome2 + importo.soldi2 + "€" );
					}

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
					gestioneImporti();
				}
			} );

			Bottone elimina = new Bottone( "Elimina" );
			elimina.setName( "" + i );

			elimina.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					aggiungiMovimenti( "Eliminato un importo » " + importo.parziali[new Integer( elimina.getName() )].getNome() );

					TotParziale[] anotherArray = new TotParziale[importo.parziali.length - 1];

					for ( int i = 0, k = 0; i < importo.parziali.length; i++ ) {

						if ( i == new Integer( elimina.getName() ) ) {
							continue;
						}
						anotherArray[k++] = importo.parziali[i];
					}

					importo.parziali = anotherArray;

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
					gestioneImporti();

				}
			} );

			JPanel gl = new JPanel( new GridBagLayout() );
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

				importo.operazione( Importo.AGGIUNGI, 0 );
				importo.setVisible( true );
				if ( importo.isDisplayable() ) {
					while ( !importo.fineInserimento ) {

					}
				}
				if ( importo.operazioneSvolta == true ) {
					aggiungiMovimenti( "Aggiunto un nuovo importo » " + importo.nome + importo.soldi + "€" );
				}

				try {
					lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
				} catch ( IOException e ) {
					e.printStackTrace();
				}
				gestioneImporti();
			}
		} );

		frame.wp.add( aggiungi );

		frame.aggiorna();
	}

	public void transazioneIn() {

		try {
			ly.removeAll();
			ly.setVisible( true );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new GridBagLayout() );

		Date date = new Date();
		Etichetta label = null;

		JPanel scheda = new JPanel( new GridBagLayout() );
		scheda.setBorder( border );
		scheda.setBackground( Finestra.coloreSfondo );

		label = new Etichetta( "Soldi :  " );
		CampoTesto soldiTXT = new CampoTesto( "0.0" );
		soldiTXT.setColumns( 4 );
		scheda.add( label, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( soldiTXT, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Data :" );
		@SuppressWarnings("deprecation")
		CampoTesto dataTXT = new CampoTesto( date.getDate() + " / " + ( date.getMonth() + 1 ) + " / " + ( date.getYear() + 1900 ) );
		scheda.add( label, new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( dataTXT, new GridBagConstraints( 4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Importo : " );
		String[] arrayImporti = new String[importo.parziali.length];
		for ( int i = 0; i < ( arrayImporti.length ); i++ ) {
			arrayImporti[i] = importo.parziali[i].getNome();
		}
		ComboBox scegliImporto = new ComboBox( arrayImporti );
		scheda.add( label, new GridBagConstraints( 6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( scegliImporto, new GridBagConstraints( 7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "//" );
		scheda.add( label, new GridBagConstraints( 8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Note : " );
		scheda.add( label, new GridBagConstraints( 9, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		CampoTesto noteTXT = new CampoTesto( "" );
		noteTXT.setColumns( 10 );
		scheda.add( noteTXT, new GridBagConstraints( 10, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		frame.wp.add( scheda, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 0, 0, 10, 0 ), 0, 0 ) );

		Bottone esegui = new Bottone( "Esegui transazione ( IN )" );
		esegui.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].addSoldi( new Double( soldiTXT.getText() ) );

				( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  "
						+ noteTXT.getText() + " * " + importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );
				frame.wp.revalidate();

				aggiungiMovimenti(
						"Transazione IN »  " + soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  " + noteTXT.getText() );
				try {
					lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		} );

		ly.add( esegui );
		Bottone refresh = new Bottone( "Refresh" );
		refresh.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  "
						+ noteTXT.getText() + " * " + importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );

				frame.wp.revalidate();
			}
		} );

		ly.add( refresh );

		try {
			AreaTesto t = new AreaTesto();
			t.setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  " + noteTXT.getText() + " * "
					+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
					+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );
			t.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 25, 50, 25, 50 ) ) );

			frame.wp.add( t, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 10, 0, 0, 0 ), 0, 0 ) );

		} catch ( NullPointerException e ) {
			displayError( "Aggiungere un nuovo importo" );
		}

		frame.aggiorna();
	}

	public void transazioneOut() {

		try {
			ly.removeAll();
			ly.setVisible( true );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new GridBagLayout() );

		JPanel scheda = new JPanel( new GridBagLayout() );
		scheda.setBorder( border );
		scheda.setBackground( Finestra.coloreSfondo );

		Date date = new Date();
		Etichetta label = null;

		label = new Etichetta( "Soldi :  " );
		CampoTesto soldiTXT = new CampoTesto( "0.0" );
		soldiTXT.setColumns( 4 );
		scheda.add( label, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( soldiTXT, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Data :" );
		@SuppressWarnings("deprecation")
		CampoTesto dataTXT = new CampoTesto( date.getDate() + " / " + ( date.getMonth() + 1 ) + " / " + ( date.getYear() + 1900 ) );
		scheda.add( label, new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( dataTXT, new GridBagConstraints( 4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Importo : " );
		String[] arrayImporti = new String[importo.parziali.length];
		for ( int i = 0; i < ( arrayImporti.length ); i++ ) {
			arrayImporti[i] = importo.parziali[i].getNome();
		}
		ComboBox scegliImporto = new ComboBox( arrayImporti );
		scheda.add( label, new GridBagConstraints( 6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( scegliImporto, new GridBagConstraints( 7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "//" );
		scheda.add( label, new GridBagConstraints( 8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Note : " );
		scheda.add( label, new GridBagConstraints( 9, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		CampoTesto noteTXT = new CampoTesto( "" );
		noteTXT.setColumns( 10 );
		scheda.add( noteTXT, new GridBagConstraints( 10, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		frame.wp.add( scheda, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 0, 0, 10, 0 ), 0, 0 ) );

		Bottone esegui = new Bottone( "Esegui transazione ( OUT )" );
		esegui.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				if ( importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() >= new Double( soldiTXT.getText() ) ) {
					importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].diminuisciSoldi( new Double( soldiTXT.getText() ) );

					( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  "
							+ noteTXT.getText() + " * " + importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );

					aggiungiMovimenti(
							"Transazione OUT »  " + soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  " + noteTXT.getText() );

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				} else {
					displayError( "Fondi insufficenti" );

					frame.aggiorna();
				}
			}
		} );

		ly.add( esegui );
		Bottone refresh = new Bottone( "Refresh" );
		refresh.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  "
						+ noteTXT.getText() + " * " + importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );
				frame.aggiorna();
			}
		} );

		ly.add( refresh );

		try {
			AreaTesto t = new AreaTesto();
			t = new AreaTesto();
			t.setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImporto.getSelectedItem().toString() + "  //  " + noteTXT.getText() + " * "
					+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getNome() + " =  "
					+ importo.parziali[cercaIndex( importo.parziali, scegliImporto.getSelectedItem().toString() )].getSoldi() + "€" );
			t.setEditable( false );
			t.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 25, 50, 25, 50 ) ) );

			frame.wp.add( t, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 10, 0, 0, 0 ), 0, 0 ) );
		} catch ( NullPointerException e ) {
			displayError( "Aggiungere un nuovo importo" );
		}

		frame.aggiorna();
	}

	public void trasferimentoSoldi() {

		try {
			ly.removeAll();
			ly.setVisible( true );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new GridBagLayout() );

		JPanel scheda = new JPanel( new GridBagLayout() );
		scheda.setBorder( border );
		scheda.setBackground( Finestra.coloreSfondo );

		Date date = new Date();
		Etichetta label = null;

		label = new Etichetta( "Soldi :  " );
		CampoTesto soldiTXT = new CampoTesto( "0.0" );
		soldiTXT.setColumns( 4 );
		scheda.add( label, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( soldiTXT, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Data :" );
		@SuppressWarnings("deprecation")
		CampoTesto dataTXT = new CampoTesto( date.getDate() + " / " + ( date.getMonth() + 1 ) + " / " + ( date.getYear() + 1900 ) );
		scheda.add( label, new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( dataTXT, new GridBagConstraints( 4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "/" );
		scheda.add( label, new GridBagConstraints( 5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Prelievo da : " );
		String[] arrayImportiPrendi = new String[importo.parziali.length];
		for ( int i = 0; i < ( arrayImportiPrendi.length ); i++ ) {
			arrayImportiPrendi[i] = importo.parziali[i].getNome();
		}
		ComboBox scegliImportoPrendi = new ComboBox( arrayImportiPrendi );
		scheda.add( label, new GridBagConstraints( 6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( scegliImportoPrendi, new GridBagConstraints( 7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "->" );
		scheda.add( label, new GridBagConstraints( 8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Trasferimento a : " );
		String[] arrayImportiDai = new String[importo.parziali.length];
		for ( int i = 0; i < ( arrayImportiDai.length ); i++ ) {
			arrayImportiDai[i] = importo.parziali[i].getNome();
		}
		ComboBox scegliImportoDai = new ComboBox( arrayImportiDai );
		scheda.add( label, new GridBagConstraints( 9, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		scheda.add( scegliImportoDai, new GridBagConstraints( 10, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "//" );
		scheda.add( label, new GridBagConstraints( 11, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		label = new Etichetta( "Note : " );
		scheda.add( label, new GridBagConstraints( 12, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );
		CampoTesto noteTXT = new CampoTesto( "" );
		noteTXT.setColumns( 10 );
		scheda.add( noteTXT, new GridBagConstraints( 13, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 25, 25, 25, 25 ), 0, 0 ) );

		frame.wp.add( scheda, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 0, 0, 10, 0 ), 0, 0 ) );

		Bottone esegui = new Bottone( "Esegui trasferimento" );
		esegui.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				if ( importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getSoldi() >= new Double( soldiTXT.getText() ) ) {
					importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].diminuisciSoldi( new Double( soldiTXT.getText() ) );
					importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].addSoldi( new Double( soldiTXT.getText() ) );

					( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImportoPrendi.getSelectedItem().toString()
							+ "  ->  " + scegliImportoDai.getSelectedItem().toString() + "  //  " + noteTXT.getText() + " * "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getNome() + " =  "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getSoldi() + "€ - "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getNome() + " =  "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getSoldi() + "€" );

					aggiungiMovimenti( "Trasferimento »  " + soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImportoDai.getSelectedItem().toString() + "  ->  "
							+ scegliImportoPrendi.getSelectedItem().toString() + "  //  " + noteTXT.getText() );

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				} else {
					displayError( "\n\n\t Fondi insufficienti" );
				}
			}
		} );

		ly.add( esegui );
		Bottone refresh = new Bottone( "Refresh" );
		refresh.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				( (AreaTesto) frame.wp.getComponent( 1 ) ).setText( soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImportoPrendi.getSelectedItem().toString() + "  ->  "
						+ scegliImportoDai.getSelectedItem().toString() + "  //  " + noteTXT.getText() + " * "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getNome() + " =  "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getSoldi() + "€ - "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getNome() + " =  "
						+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getSoldi() + "€" );
				frame.aggiorna();
			}
		} );

		ly.add( refresh );

		try {
			AreaTesto t = new AreaTesto();
			t = new AreaTesto();
			t.setText(
					soldiTXT.getText() + "€  /  " + dataTXT.getText() + "  /  " + scegliImportoPrendi.getSelectedItem().toString() + "  ->  " + scegliImportoDai.getSelectedItem().toString()
							+ "  //  " + noteTXT.getText() + " * " + importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getNome() + " =  "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoPrendi.getSelectedItem().toString() )].getSoldi() + "€ - "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getNome() + " =  "
							+ importo.parziali[cercaIndex( importo.parziali, scegliImportoDai.getSelectedItem().toString() )].getSoldi() + "€" );
			t.setEditable( false );
			t.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 25, 50, 25, 50 ) ) );

			frame.wp.add( t, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 10, 0, 0, 0 ), 0, 0 ) );

		} catch ( NullPointerException e ) {
			displayError( "Aggiungere nuovi importi" );
		}

		frame.aggiorna();
	}

	public void MostraMovimenti() {

		try {
			ly.removeAll();
			ly.setVisible( false );
			frame.sc.setVisible( true );
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
		}

		frame.wp.setLayout( new WrapLayout() );

		JPanel g = new JPanel( new GridBagLayout() );
		g.setBackground( Finestra.coloreSfondo );
		AreaTesto t;

		for ( int i = movimenti.length - 1, k = 0; i > -1; i--, k++ ) {

			t = new AreaTesto();
			t.setText( ( i + 1 ) + " • " + movimenti[i] );
			t.setEditable( false );

			JPanel bottoni = new JPanel( new WrapLayout() );
			bottoni.setBackground( Finestra.coloreSfondo );
			bottoni.setBorder( BorderFactory.createEmptyBorder() );

			Bottone modifica = new Bottone( "Modifica" );
			modifica.setName( "" + i );
			modifica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					new ModificaMovimenti( new Integer( modifica.getName() ) );
				}
			} );
			bottoni.add( modifica );

			Bottone rimuovi = new Bottone( "Rimuovi" );
			rimuovi.setName( "" + i );
			rimuovi.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					String[] anotherArray = new String[movimenti.length - 1];

					for ( int i = 0, k = 0; i < movimenti.length; i++ ) {

						if ( i == new Integer( rimuovi.getName() ) ) {
							continue;
						}
						anotherArray[k++] = movimenti[i];
					}

					movimenti = anotherArray;

					MostraMovimenti();

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException | NullPointerException e ) {
					}

				}
			} );
			bottoni.add( rimuovi );

			g.add( t, new GridBagConstraints( 0, k, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 10, 0, 10, 0 ), 0, 0 ) );
			g.add( bottoni, new GridBagConstraints( 1, k, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 10, 0, 10, 0 ), 0, 0 ) );
		}

		frame.wp.add( g );

		frame.aggiorna();
	}

	public void aggiungiMovimenti( String s ) {

		String[] array = movimenti;
		movimenti = new String[movimenti.length + 1];
		for ( int i = 0; i < ( movimenti.length - 1 ); i++ ) {
			movimenti[i] = array[i];
		}
		movimenti[movimenti.length - 1] = s;
	}

	public void displayError( String messaggio ) {

		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().createImage( "some-icon.png" );
		TrayIcon trayIcon = new TrayIcon( image, "Homework" );

		trayIcon.setImageAutoSize( true );
		trayIcon.setToolTip( "System tray icon demo" );
		try {
			tray.add( trayIcon );
		} catch ( AWTException e1 ) {
			e1.printStackTrace();
		}

		trayIcon.displayMessage( "Errore", messaggio, MessageType.ERROR );
	}

	public static void main( String[] args ) {

		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {

				Interfaccia interfaccia = new Interfaccia();
				interfaccia.creaframe();
			}
		} );
	}

	class ModificaMovimenti extends JDialog {

		private static final long serialVersionUID = 1L;

		private Bottone modifica = new Bottone( "Modifica" );
		private JPanel griglia = new JPanel( new GridBagLayout() );

		public ModificaMovimenti( int id ) {

			super( frame, "", true );

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setLayout( new BorderLayout() );
			setResizable( false );
			setLocation( ( (int) screenSize.getWidth() / 2 ) - 250, ( (int) screenSize.getHeight() / 2 ) - 75 );
			setSize( 500, 150 );
			setTitle( "Modifica movimento" );

			griglia.setBackground( Finestra.coloreSfondo );
			add( griglia, BorderLayout.CENTER );

			add( modifica, BorderLayout.SOUTH );

			CampoTesto testo = new CampoTesto();
			testo.setText( movimenti[id] );
			testo.setColumns( 40 );
			griglia.add( testo, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 0, 0, 0, 0 ), 0, 0 ) );

			modifica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					dispose();
					movimenti[id] = testo.getText();
					MostraMovimenti();

					try {
						lettore.salvaDati( importo.parziali, movimenti, importo.getColoriImporti() );
					} catch ( IOException | NullPointerException e ) {
					}
				}
			} );
			setVisible( true );
		}
	}
}
