package Links;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Homework.GestoreCompiti;
import main.AreaTesto;
import main.Bottone;
import main.ComboBox;
import main.Etichetta;
import main.Finestra;
import main.MainGestore;
import main.Menu;
import main.MenuItem;

public class MainLinks {

	final Finestra frame = new Finestra( "Links" );

	GestoreLink gLink = new GestoreLink( frame );

	Lettore lettore = new Lettore();

	public void creaFrame() {

		setPosizione();

		Menu menu = new Menu( "Finestra" );
		MenuItem menuItem;

		menuItem = new MenuItem( "HOME" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				home();
			}
		} );

		menuItem = new MenuItem( "Gestisci Link" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				finestraLink();
			}
		} );

		menuItem = new MenuItem( "Preferenze all'avvio" );
		menu.add( menuItem );
		menuItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				Preferenze p = new Preferenze( frame );
				if ( p.isDisplayable() ) {
					while ( !p.fineInserimento ) {
					}
				}
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
			gLink.links = lettore.leggiLinks();
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		try {
			lettore.salvaDati( gLink.links, gLink.coloriLinks() );
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		if ( gLink.links.length > 0 ) {
			home();
		} else {
			finestraLink();
		}

		frame.setVisible( true );

	}

	public void home() {
		try {
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		}

		for ( int i = 0; i < gLink.links.length; i++ ) {

			Bottone bottone = new Bottone( gLink.links[i].getHTMLComponent() );
			bottone.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( gLink.links[i].getColore(), 3 ), BorderFactory.createEmptyBorder( 0, 20, 20, 20 ) ) );
			bottone.setName( "" + i );

			bottone.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					String link = gLink.links[new Integer( bottone.getName() )].getLink();

					try {
						Runtime.getRuntime().exec( "explorer.exe /open," + link );
					} catch ( IOException e ) {
						e.printStackTrace();
					}

					// frame.setExtendedState( Finestra.ICONIFIED );
				}
			} );

			frame.wp.add( bottone );
		}

		frame.aggiorna();
	}

	public void finestraLink() {
		try {
			frame.pulisciLayout();
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		}

		JPanel g;
		AreaTesto t;

		for ( int i = 0; i < gLink.links.length; i++ ) {

			g = new JPanel( new GridLayout( 2, 1 ) );
			g.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( gLink.links[i].getColore(), 2 ), BorderFactory.createEmptyBorder( 30, 20, 10, 20 ) ) );
			g.setBackground( Finestra.coloreSfondo );
			t = new AreaTesto();
			t.setText( gLink.links[i].getComponent() );
			t.setEditable( false );
			g.add( t );

			Bottone copia = new Bottone( "Copia" );
			copia.setName( "" + i );
			copia.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					StringSelection stringSelection = new StringSelection( gLink.links[new Integer( copia.getName() )].getLink() );
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents( stringSelection, null );
				}
			} );

			Bottone apri = new Bottone( "Apri" );
			apri.setName( "" + i );
			apri.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					String link = gLink.links[new Integer( apri.getName() )].getLink();

					try {
						Runtime.getRuntime().exec( "explorer.exe /open," + link );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				}
			} );

			Bottone modifica = new Bottone( "Modifica" );
			modifica.setName( "" + i );
			modifica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					gLink.operazione( GestoreLink.MODIFICA, new Integer( modifica.getName() ) );
					gLink.setVisible( true );
					if ( gLink.isDisplayable() ) {
						while ( !gLink.fineInserimento ) {
						}
					}

					try {
						lettore.salvaDati( gLink.links, gLink.coloriLinks() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}

					finestraLink();
				}
			} );

			Bottone elimina = new Bottone( "Elimina" );
			elimina.setName( "" + i );

			elimina.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					gLink.rimuoviLink( new Integer( elimina.getName() ) );

					try {
						lettore.salvaDati( gLink.links, gLink.coloriLinks() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}

					finestraLink();
				}
			} );

			JPanel gl = new JPanel( new GridBagLayout() );
			gl.add( copia, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 2, 2, 2, 2 ), 0, 0 ) );
			gl.add( apri, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 2, 2, 2, 2 ), 0, 0 ) );
			gl.add( modifica, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 2, 2, 2, 2 ), 0, 0 ) );
			gl.add( elimina, new GridBagConstraints( 1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 2, 2, 2, 2 ), 0, 0 ) );
			gl.setBackground( Finestra.coloreSfondo );
			g.add( gl );

			frame.wp.add( g );
		}

		Bottone aggiungi = new Bottone( "+" );
		aggiungi.setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 50 ) );
		aggiungi.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				gLink.operazione( GestoreCompiti.AGGIUNGI, 0 );
				gLink.setVisible( true );
				if ( gLink.isDisplayable() ) {
					while ( !gLink.fineInserimento ) {
					}
				}

				try {
					lettore.salvaDati( gLink.links, gLink.coloriLinks() );
				} catch ( IOException e ) {
					e.printStackTrace();
				}

				finestraLink();
			}

		} );

		frame.wp.add( aggiungi );

		frame.aggiorna();
	}

	public void setPosizione() {
		String[] preferenze = lettore.leggiPreferenze();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		switch (preferenze[0]) {

			case "Grandezza media": {
				frame.setExtendedState( Finestra.NORMAL );

				switch (preferenze[1]) {

					case "Centrale": {
						frame.setLocation( ( ( screenSize.width / 2 ) - ( frame.getWidth() / 2 ) ), frame.getY() );
						break;
					}

					case "A destra": {
						frame.setLocation( ( screenSize.width - ( frame.getWidth() + 25 ) ), frame.getY() );
						break;
					}

					case "A sinistra": {
						frame.setLocation( 25, frame.getY() );
						break;
					}
				}

				switch (preferenze[2]) {

					case "Centrale": {
						frame.setLocation( frame.getX(), ( screenSize.height / 2 ) - ( frame.getHeight() / 2 ) );
						break;
					}

					case "In alto": {
						frame.setLocation( frame.getX(), 25 );
						break;
					}

					case "In basso": {
						frame.setLocation( frame.getX(), ( screenSize.height - ( frame.getHeight() + 50 ) ) );
						break;
					}
				}
				break;
			}

			case "Grandezza minima": {
				frame.setExtendedState( Finestra.NORMAL );
				frame.setSize( frame.getMinimumSize() );

				switch (preferenze[1]) {

					case "Centrale": {
						frame.setLocation( ( ( screenSize.width / 2 ) - ( frame.getWidth() / 2 ) ), frame.getY() );
						break;
					}

					case "A destra": {
						frame.setLocation( ( screenSize.width - ( frame.getWidth() + 25 ) ), frame.getY() );
						break;
					}

					case "A sinistra": {
						frame.setLocation( 25, frame.getY() );
						break;
					}
				}

				switch (preferenze[2]) {

					case "Centrale": {
						frame.setLocation( frame.getX(), ( screenSize.height / 2 ) - ( frame.getHeight() / 2 ) );
						break;
					}

					case "In alto": {
						frame.setLocation( frame.getX(), 25 );
						break;
					}

					case "In basso": {
						frame.setLocation( frame.getX(), ( screenSize.height - ( frame.getHeight() + 50 ) ) );
						break;
					}
				}

				break;
			}

			default: {
				frame.setExtendedState( Finestra.MAXIMIZED_BOTH );
			}
		}
	}

	public static void main( String[] args ) {

		MainLinks main = new MainLinks();
		main.creaFrame();
	}

	class Preferenze extends JDialog {

		private static final long serialVersionUID = 1L;

		boolean fineInserimento;

		public Preferenze( JFrame frame ) {
			super( frame, "", true );
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setSize( 500, 175 );
			setLocation( ( screenSize.width / 2 ) - ( getWidth() / 2 ), ( screenSize.height / 2 ) - ( getHeight() / 2 ) );
			setTitle( "Preferenze all'avvio" );
			setResizable( false );
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setLayout( new BorderLayout() );

			JPanel gl = new JPanel( new GridLayout( 3, 2 ) );
			gl.setBackground( Finestra.coloreSfondo );

			Etichetta label = new Etichetta( "Grandezza finestra : " );
			gl.add( label );

			String[] grandezze = { "Tutto schermo", "Grandezza media", "Grandezza minima" };
			ComboBox grandezza = new ComboBox( grandezze );
			grandezza.setSelectedItem( lettore.leggiPreferenze()[0] );
			gl.add( grandezza );

			label = new Etichetta( "Posizione orizzontale della finestra : " );
			gl.add( label );

			String[] pOrizzontali = { "Centrale", "A destra", "A sinistra" };
			ComboBox pOrizzontale = new ComboBox( pOrizzontali );
			pOrizzontale.setSelectedItem( lettore.leggiPreferenze()[1] );
			gl.add( pOrizzontale );

			label = new Etichetta( "Posizione verticale della finestra : " );
			gl.add( label );

			String[] pVerticali = { "Centrale", "In alto", "In basso" };
			ComboBox pVerticale = new ComboBox( pVerticali );
			pVerticale.setSelectedItem( lettore.leggiPreferenze()[2] );
			gl.add( pVerticale );

			Bottone applica = new Bottone( "Applica" );
			add( gl, BorderLayout.CENTER );
			add( applica, BorderLayout.SOUTH );
			applica.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {

					try {
						lettore.salvaPreferenze( grandezza.getSelectedItem().toString(), pOrizzontale.getSelectedItem().toString(), pVerticale.getSelectedItem().toString() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}

					dispose();
					fineInserimento = true;

					frame.dispose();

					MainLinks.main( null );
				}
			} );
			setVisible( true );
			fineInserimento = false;
		}
	}
}
