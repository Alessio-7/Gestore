package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LettoreColori {

	File f = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\colori.txt" );

	public void salvaDati( Color colori[] ) {

		if ( f.exists() ) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter( f );
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			}

			if ( colori.length > 0 ) {
				for ( Color c : colori ) {

					pw.print( c.getRed() + "," + c.getGreen() + "," + c.getBlue() );
					pw.append( "ª" );
				}
			} else {
				f.delete();
			}

			pw.close();
		} else {
			File dire = new File( f.getParent() );
			if ( !dire.exists() ) {
				dire.mkdirs();
			}
			try {
				if ( f.createNewFile() ) {

					PrintWriter pw = new PrintWriter( f );

					if ( colori.length > 0 ) {
						for ( Color c : colori ) {

							pw.print( c.getRed() + "," + c.getGreen() + "," + c.getBlue() );
							pw.append( "ª" );
						}
					} else {
						f.delete();
					}

					pw.close();
				}
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

	}

	private Color[] aggiungiColor( Color[] c, Color Color ) {
		Color[] clone = new Color[c.length + 1];

		for ( int i = 0; i < c.length; i++ ) {
			clone[i] = c[i];
		}
		clone[clone.length - 1] = Color;
		return clone;
	}

	public Color[] leggiColori() {

		Color[] ritorno = new Color[0];

		if ( f.exists() ) {

			char[] in = new char[(int) f.length()];

			FileReader r;
			BufferedReader b;
			try {
				r = new FileReader( f );
				b = new BufferedReader( r );
				b.read( in );
			} catch ( FileNotFoundException e ) {
			} catch ( IOException e ) {
			}

			String testoIntero = String.copyValueOf( in );

			try {

				String[] coloriArr = testoIntero.split( "ª" );
				String[] dati = null;

				for ( int i = 0; i < coloriArr.length; i++ ) {
					dati = coloriArr[i].split( "," );
					ritorno = aggiungiColor( ritorno, new Color( Integer.parseInt( dati[0] ), Integer.parseInt( dati[1] ), Integer.parseInt( dati[2] ) ) );

					/*
					 * Sfondo, Etichetta, AreaTesto = 0, foreground = 1, Menu = 2, bordo menu = 3,
					 * Bottone = 4, colore container = 5
					 * 
					 */
				}
			} catch ( Exception e ) {
			}
		} else {
			Color[] coloriPredefiniti = { new Color( 255, 255, 255 ), new Color( 0, 0, 0 ), new Color( 242, 242, 242 ), new Color( 204, 204, 204 ), new Color( 240, 240, 240 ),
					new Color( 242, 242, 242 ) };
			return coloriPredefiniti;
		}

		return ritorno;

	}
}
