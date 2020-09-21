package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Etichetta extends JLabel {

	private static final long serialVersionUID = 1;

	public static Color sfondo = MainGestore.colori.leggiColori()[0];
	public static Color foreground = MainGestore.colori.leggiColori()[1];

	public Etichetta( String testo ) {
		setText( testo );
		setBackground( sfondo );
		setForeground( foreground );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
	}
}
