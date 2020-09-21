package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Menu extends JMenu {

	private static final long serialVersionUID = 1L;

	public static Color sfondo = MainGestore.colori.leggiColori()[2];
	public static Color foreground = MainGestore.colori.leggiColori()[1];
	public static Color bordo = MainGestore.colori.leggiColori()[3];

	public Menu( String s ) {
		UIManager.put( "PopupMenu.border", new LineBorder( bordo ) );

		setText( s );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setForeground( foreground );
		setOpaque( true );
		setBackground( sfondo );// new Color( 66, 67, 70 )
		setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( sfondo ), BorderFactory.createEmptyBorder() ) );
	}

	public void aggiungiSeparatore() {
		JSeparator sep = new JSeparator();
		sep.setForeground( foreground );
		sep.setBackground( sfondo );

		add( sep );
	}
}
