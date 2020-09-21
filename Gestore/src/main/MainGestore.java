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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Contabilita.Interfaccia;
import Homework.MainHomework;

public class MainGestore {

	public final Color coloreSfondoDark = new Color( 47, 47, 47 );
	public final Color coloreMenuBarDark = new Color( 66, 67, 70 );
	public final Color coloreTestoDark = new Color( 255, 255, 255 );
	public final Color coloreBottoneDark = new Color( 30, 30, 30 );

	public final Border border = BorderFactory.createLineBorder( Color.WHITE );

	public final Font font = new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 );

	public static LettoreColori colori = new LettoreColori();

	public void finestraGestore() {
		JFrame frame = new JFrame( "Scelta gestore" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 500, 360 );
		frame.setResizable( false );
		frame.setLayout( new GridLayout( 2, 1 ) );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( ( screenSize.width / 2 ) - ( frame.getWidth() / 2 ), ( screenSize.height / 2 ) - 400 );

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( MainGestore.colori.leggiColori()[3] ), BorderFactory.createEmptyBorder( 3, 3, 3, 0 ) ) );
		menuBar.setBackground( colori.leggiColori()[2] );

		Bottone menu = new Bottone( "Preferenze" );
		menu.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				Preferenze p = new Preferenze( frame );
				if ( p.isDisplayable() ) {
					while ( !p.fineInserimento ) {
					}
				}
				frame.dispose();

				SystemTray tray = SystemTray.getSystemTray();
				Image image = Toolkit.getDefaultToolkit().createImage( "some-icon.png" );
				TrayIcon trayIcon = new TrayIcon( image, "Homework" );

				trayIcon.setImageAutoSize( true );
				trayIcon.setToolTip( "System tray icon demo" );
				try {
					tray.add( trayIcon );
				} catch ( AWTException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				trayIcon.displayMessage( "ATTENZIONE", "Riavviare il programma", MessageType.WARNING );
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

		frame.setVisible( true );
	}

	public static void main( String[] args ) {

		MainGestore g = new MainGestore();
		g.finestraGestore();

	}

}

class Preferenze extends JDialog {

	boolean fineInserimento;

	LettoreColori colori = new LettoreColori();

	public Preferenze( JFrame frame ) {
		super( frame, "", true );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize( 500, 150 );
		setLocation( ( screenSize.width / 2 ) - ( getWidth() / 2 ), ( screenSize.height / 2 ) - ( getHeight() / 2 ) );
		setTitle( "Preferenze" );
		setResizable( false );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLayout( new BorderLayout() );

		JPanel gl = new JPanel( new GridLayout( 1, 2 ) );
		gl.setBackground( colori.leggiColori()[0] );
		Etichetta label = new Etichetta( "Tema: " );
		gl.add( label );

		String[] temi = { "Chiaro", "Scuro" };
		JComboBox<String> tema = new JComboBox<>( temi );
		gl.add( tema );

		JPanel wp = new JPanel( new WrapLayout() );
		wp.setBackground( colori.leggiColori()[0] );
		Bottone applica = new Bottone( "Applica" );
		wp.add( applica );
		add( gl, BorderLayout.CENTER );
		add( wp, BorderLayout.SOUTH );
		applica.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				LettoreColori l = new LettoreColori();

				if ( tema.getSelectedItem() == "Chiaro" ) {
					Color[] coloriChiari = { new Color( 255, 255, 255 ), new Color( 0, 0, 0 ), new Color( 242, 242, 242 ), new Color( 204, 204, 204 ), new Color( 240, 240, 240 ),
							new Color( 204, 204, 204 ) };
					l.salvaDati( coloriChiari );
				} else {
					Color[] coloriScuri = { new Color( 47, 47, 47 ), new Color( 255, 255, 255 ), new Color( 66, 67, 70 ), Color.DARK_GRAY, new Color( 30, 30, 30 ),
							new Color( 204, 204, 204 ) };
					l.salvaDati( coloriScuri );
				}

				dispose();
				fineInserimento = true;
			}
		} );
		setVisible( true );
		fineInserimento = false;
	}
}
