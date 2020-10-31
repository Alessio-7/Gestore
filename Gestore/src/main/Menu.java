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

	public final static Color sfondo = MainGestore.colori.leggiColori()[2];
	public final static Color foreground = MainGestore.colori.leggiColori()[1];
	public final static Color bordo = MainGestore.colori.leggiColori()[3];

	public Menu( String s ) {
		UIManager.put( "PopupMenu.border", new LineBorder( bordo ) );

		setText( s );
		setFont( new Font( "Microsoft New Tai Lue", Font.PLAIN, 12 ) );
		setForeground( foreground );
		setOpaque( true );
		setBackground( sfondo );
		setBorder( BorderFactory.createLineBorder( bordo ) );
	}

	public void aggiungiSeparatore() {
		add( getSeparatore() );
	}

	public static JSeparator getSeparatore() {

		JSeparator sep = new JSeparator();
		sep.setForeground( foreground );
		sep.setBackground( sfondo );

		return sep;
	}
}
