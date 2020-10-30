package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Finestra extends JFrame {

	private static final long serialVersionUID = 1;

	public JPanel wp = new JPanel( new WrapLayout() );
	public PannelloScorrimento sc = new PannelloScorrimento( wp );
	public JMenuBar menuBar = new JMenuBar();

	public static Color coloreSfondo = MainGestore.colori.leggiColori()[0];
	public static Color coloreMenu = MainGestore.colori.leggiColori()[2];
	public static Color coloreContainer = MainGestore.colori.leggiColori()[5];

	public Finestra( String nome ) {
		setTitle( nome );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( 960, 540 );
		setMinimumSize( new Dimension( 640, 360 ) );
		setExtendedState( Frame.MAXIMIZED_BOTH );
		setLayout( new BorderLayout() );
		add( sc, BorderLayout.CENTER );
		setJMenuBar( menuBar );
		wp.setBackground( coloreSfondo );
	}

	public void setMenuBar( Menu menu ) {
		menuBar.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( MainGestore.colori.leggiColori()[3] ), BorderFactory.createEmptyBorder( 3, 3, 3, 0 ) ) );
		menuBar.setBackground( coloreMenu );
		menuBar.add( menu );
	}

	public void pulisciLayout() {
		wp.removeAll();
		aggiorna();
	}

	public void aggiorna() {
		wp.repaint();
		revalidate();
	}
}
