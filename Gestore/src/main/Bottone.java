package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Bottone extends JButton {

	private static final long serialVersionUID = 1;

	public static Color sfondo = MainGestore.colori.leggiColori()[4];
	public static Color foreground = MainGestore.colori.leggiColori()[1];

	public Bottone( String nome ) {
		setText( nome );
		setBackground( sfondo );// new Color( 30, 30, 30 )
		setForeground( foreground );// new Color( 255, 255, 255 )
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) ) );
	}
}
