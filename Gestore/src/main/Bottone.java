package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Bottone extends JButton {

	private static final long serialVersionUID = 1;

	public final static Color sfondo = MainGestore.colori.leggiColori()[4];
	public final static Color foreground = MainGestore.colori.leggiColori()[1];
	public static final Border bordo = BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) );

	public Bottone( String nome ) {
		setText( nome );
		setBackground( sfondo );
		setForeground( foreground );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setBorder( bordo );
		setFocusPainted( false );
	}
}
