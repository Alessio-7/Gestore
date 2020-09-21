package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class AreaTesto extends JTextArea {

	private static final long serialVersionUID = 1L;

	public static Color sfondo = MainGestore.colori.leggiColori()[0];
	public static Color foreground = MainGestore.colori.leggiColori()[1];

	public AreaTesto() {
		setBackground( sfondo );
		setForeground( foreground );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setEditable( false );
	}
}
