package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

public class MenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public static Color sfondo = MainGestore.colori.leggiColori()[2];
	public static Color foreground = MainGestore.colori.leggiColori()[1];

	public MenuItem( String s ) {
		setText( s );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setForeground( foreground );
		setBackground( sfondo );
		setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( sfondo ), BorderFactory.createEmptyBorder() ) );
	}

}
