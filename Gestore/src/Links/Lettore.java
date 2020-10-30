package Links;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lettore {

	File f = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\Links\\dati.txt" );
	File coloriLink = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\Links\\colori link.txt" );
	File preferenze = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\Links\\preferenze.txt" );

	public void salvaDati( Link[] links, Color[] cLinks ) throws IOException {

		if ( f.exists() ) {

			PrintWriter pw = new PrintWriter( f );

			if ( links.length > 0 ) {
				pw.print( "" );
				for ( int i = 0; i < links.length; i++ ) {
					pw.append( links[i].getNome() + "¦" + links[i].getLink() + "ª" );
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
			if ( f.createNewFile() ) {

				PrintWriter pw = new PrintWriter( f );

				if ( links.length > 0 ) {
					pw.print( "" );
					for ( int i = 0; i < links.length; i++ ) {
						pw.append( links[i].getNome() + "¦" + links[i].getLink() + "ª" );
					}
				} else {
					f.delete();
				}

				pw.close();
			}

		}

		if ( coloriLink.exists() ) {

			PrintWriter pw = new PrintWriter( coloriLink );

			if ( cLinks.length > 0 ) {
				pw.print( "" );
				try {
					for ( Color c : cLinks ) {
						pw.append( c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ";" );
					}
				} catch ( NullPointerException e ) {
					for ( int i = 0; i < cLinks.length; i++ ) {
						pw.append( "204,204,204;" );
					}
				}
			} else {
				coloriLink.delete();
			}

			pw.close();

		} else {

			File dire = new File( coloriLink.getParent() );
			if ( !dire.exists() ) {
				dire.mkdirs();
			}
			if ( coloriLink.createNewFile() ) {

				PrintWriter pw = new PrintWriter( coloriLink );

				if ( cLinks.length > 0 ) {
					pw.print( "" );
					try {
						for ( Color c : cLinks ) {
							pw.append( c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ";" );
						}
					} catch ( NullPointerException e ) {
						for ( int i = 0; i < cLinks.length; i++ ) {
							pw.append( "204,204,204;" );
						}
					}
				} else {
					coloriLink.delete();
				}

				pw.close();
			}

		}
	}

	public void salvaPreferenze( String grandezza, String pOizzontale, String pVerticale ) throws IOException {

		if ( preferenze.exists() ) {

			PrintWriter pw = new PrintWriter( preferenze );

			pw.print( grandezza + ";" + pOizzontale + ";" + pVerticale + ";" );

			pw.close();

		} else {

			File dire = new File( f.getParent() );
			if ( !dire.exists() ) {
				dire.mkdirs();
			}
			if ( preferenze.createNewFile() ) {

				PrintWriter pw = new PrintWriter( preferenze );

				pw.print( grandezza + ";" + pOizzontale + ";" + pVerticale + ";" );

				pw.close();
			}

		}
	}

	public Link[] leggiLinks() throws IOException {

		Link[] ritorno = new Link[0];

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

				String[] linkArr = testoIntero.split( "ª" );
				String[] dati = null;
				Color[] coloriLink = leggiColoriLink( linkArr.length );

				for ( int i = 0; i < linkArr.length; i++ ) {
					dati = linkArr[i].split( "¦" );
					ritorno = aggiungiLink( ritorno, new Link( dati[0], dati[1], coloriLink[i] ) );
				}
			} catch ( Exception e ) {
			}
		}

		return ritorno;

	}

	private Color[] leggiColoriLink( int nLink ) {
		Color[] ritorno = new Color[0];

		if ( coloriLink.exists() ) {

			char[] in = new char[(int) coloriLink.length()];

			FileReader r;
			BufferedReader b;
			try {
				r = new FileReader( coloriLink );
				b = new BufferedReader( r );
				b.read( in );
			} catch ( FileNotFoundException e ) {
			} catch ( IOException e ) {
			}

			String testoIntero = String.copyValueOf( in );

			try {

				String[] coloriArr = testoIntero.split( ";" );
				String[] dati = null;

				for ( int i = 0; i < coloriArr.length; i++ ) {
					dati = coloriArr[i].split( "," );
					ritorno = aggiungiColor( ritorno, new Color( Integer.parseInt( dati[0] ), Integer.parseInt( dati[1] ), Integer.parseInt( dati[2] ) ) );
				}
			} catch ( Exception e ) {
			}
		} else {
			Color[] coloriPredefiniti = new Color[nLink];
			for ( int i = 0; i < nLink; i++ ) {
				coloriPredefiniti = aggiungiColor( coloriPredefiniti, new Color( 204, 204, 204 ) );
			}

			return coloriPredefiniti;
		}
		return ritorno;
	}

	public String[] leggiPreferenze() {

		String[] ritorno = new String[3];

		if ( preferenze.exists() ) {

			char[] in = new char[(int) preferenze.length()];

			FileReader r;
			BufferedReader b;
			try {
				r = new FileReader( preferenze );
				b = new BufferedReader( r );
				b.read( in );
			} catch ( FileNotFoundException e ) {
			} catch ( IOException e ) {
			}

			String testoIntero = String.copyValueOf( in );

			ritorno = testoIntero.split( ";" );
		} else {

			String[] prefPredefinite = { "Tutto schermo", "Centrale", "Centrale" };
			return prefPredefinite;

		}
		return ritorno;

	}

	private Color[] aggiungiColor( Color[] c, Color Color ) {
		Color[] clone = new Color[c.length + 1];

		for ( int i = 0; i < c.length; i++ ) {
			clone[i] = c[i];
		}
		clone[clone.length - 1] = Color;
		return clone;
	}

	public Link[] aggiungiLink( Link[] links, Link link ) {

		Link[] clone = new Link[links.length + 1];

		for ( int i = 0; i < links.length; i++ ) {
			clone[i] = links[i];
		}
		clone[clone.length - 1] = link;

		return clone;
	}
}
