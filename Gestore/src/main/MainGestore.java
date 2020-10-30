package main;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Contabilita.Interfaccia;
import Homework.MainHomework;
import Links.MainLinks;

public class MainGestore {

	public final Color coloreSfondoDark = new Color( 47, 47, 47 );
	public final Color coloreMenuBarDark = new Color( 66, 67, 70 );
	public final Color coloreTestoDark = new Color( 255, 255, 255 );
	public final Color coloreBottoneDark = new Color( 30, 30, 30 );

	public final Border border = BorderFactory.createLineBorder( Color.WHITE );

	public final Font font = new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 );

	public static Lettore colori = new Lettore();

	public static void finestraGestore() {
		JFrame frame = new JFrame( "Scelta gestore" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 500, 499 );
		frame.setResizable( false );
		frame.setLayout( new GridLayout( 3, 1 ) );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( ( screenSize.width / 2 ) - ( frame.getWidth() / 2 ), ( screenSize.height / 2 ) - 400 );

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( MainGestore.colori.leggiColori()[3] ), BorderFactory.createEmptyBorder( 3, 3, 3, 0 ) ) );
		menuBar.setBackground( colori.leggiColori()[0] );

		Bottone menu = new Bottone( "Preferenze" );
		menu.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				Preferenze p = new Preferenze( frame );
				if ( p.isDisplayable() ) {
					while ( !p.fineInserimento ) {
					}
				}
			}
		} );

		menuBar.add( menu );

		frame.setJMenuBar( menuBar );

		Bottone b = new Bottone( "Contabilità" );
		b.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				Interfaccia.main( null );
				frame.dispose();
			}
		} );
		frame.add( b );

		b = new Bottone( "Homework" );
		b.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				MainHomework.main( null );
				frame.dispose();
			}
		} );
		frame.add( b );

		b = new Bottone( "Links" );
		b.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				MainLinks.main( null );
				frame.dispose();
			}
		} );
		frame.add( b );

		frame.setVisible( true );
	}

	public static void main( String[] args ) {

		switch (colori.leggiAvvio()) {
			case "Scelta gestori": {
				MainGestore.finestraGestore();
				break;
			}
			case "Contabilità": {
				Interfaccia.main( null );
				break;
			}
			case "Homework": {
				MainHomework.main( null );
				break;
			}
			case "Links": {
				MainLinks.main( null );
				break;
			}
			default: {
				MainGestore.finestraGestore();
			}
		}
	}

}

class Preferenze extends JDialog {

	private static final long serialVersionUID = 1L;

	boolean fineInserimento;

	Lettore colori = new Lettore();

	public Preferenze( JFrame frame ) {
		super( frame, "", true );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize( 500, 150 );
		setLocation( ( screenSize.width / 2 ) - ( getWidth() / 2 ), ( screenSize.height / 2 ) - ( getHeight() / 2 ) );
		setTitle( "Preferenze" );
		setResizable( false );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLayout( new BorderLayout() );

		JPanel gl = new JPanel( new GridLayout( 2, 2 ) );
		gl.setBackground( colori.leggiColori()[0] );
		Etichetta label = new Etichetta( "Tema: " );
		gl.add( label );

		String[] temi = { "Chiaro", "Scuro" };
		ComboBox tema = new ComboBox( temi );
		tema.setSelectedIndex( gl.getBackground().getBlue() == 47 ? 1 : 0 );
		gl.add( tema );

		label = new Etichetta( "All'avvio: " );
		gl.add( label );

		String[] gestori = { "Scelta gestori", "Contabilità", "Homework", "Links" };
		ComboBox gestore = new ComboBox( gestori );
		gestore.setSelectedItem( colori.leggiAvvio() );
		gl.add( gestore );

		Bottone applica = new Bottone( "Applica" );
		add( gl, BorderLayout.CENTER );
		add( applica, BorderLayout.SOUTH );
		applica.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				Lettore l = new Lettore();

				if ( tema.getSelectedItem() == "Chiaro" ) {
					Color[] coloriChiari = { new Color( 255, 255, 255 ), new Color( 0, 0, 0 ), new Color( 242, 242, 242 ), new Color( 204, 204, 204 ), new Color( 240, 240, 240 ),
							new Color( 204, 204, 204 ) };
					l.salvaDati( coloriChiari, (String) gestore.getSelectedItem() );
				} else {
					Color[] coloriScuri = { new Color( 47, 47, 47 ), new Color( 255, 255, 255 ), new Color( 66, 67, 70 ), Color.DARK_GRAY, new Color( 30, 30, 30 ),
							new Color( 204, 204, 204 ) };
					l.salvaDati( coloriScuri, (String) gestore.getSelectedItem() );
				}
				dispose();
				fineInserimento = true;

				frame.dispose();

				SystemTray tray = SystemTray.getSystemTray();
				Image image = Toolkit.getDefaultToolkit().createImage( "some-icon.png" );
				TrayIcon trayIcon = new TrayIcon( image, "Homework" );

				trayIcon.setImageAutoSize( true );
				trayIcon.setToolTip( "System tray icon demo" );
				try {
					tray.add( trayIcon );
				} catch ( AWTException e ) {
					e.printStackTrace();
				}

				trayIcon.displayMessage( "ATTENZIONE", "Riavviare il programma", MessageType.WARNING );
			}
		} );
		setVisible( true );
		fineInserimento = false;
	}
}
