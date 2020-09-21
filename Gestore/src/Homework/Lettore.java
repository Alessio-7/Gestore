package Homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lettore {

	File f = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\Homework\\dati.txt" );
	File f1 = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\Homework\\preferenze.txt" );

	public void salvaDati( Compito[] compiti, double difficoltà, int anticipo ) {

		if ( f.exists() ) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter( f );
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			}

			if ( compiti.length > 0 ) {
				for ( int i = 0; i < compiti.length; i++ ) {

					pw.print( compiti[i].getMateria() + "¦" + compiti[i].getCompito() + "¦" + compiti[i].getData() + "¦" + compiti[i].getOra() + "¦" + compiti[i].getDifficoltà() );
					pw.append( 'ª' );
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

					if ( compiti.length > 0 ) {
						for ( int i = 0; i < compiti.length; i++ ) {

							pw.print( compiti[i].getMateria() + "¦" + compiti[i].getCompito() + "¦" + compiti[i].getData() + "¦" + compiti[i].getOra() + "¦" + compiti[i].getDifficoltà() );
							pw.append( 'ª' );
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

		if ( f1.exists() ) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter( f1 );
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			}

			pw.print( difficoltà + "#" + anticipo );

			pw.close();
		} else {
			File dire = new File( f1.getParent() );
			if ( !dire.exists() ) {
				dire.mkdirs();
			}
			try {
				if ( f1.createNewFile() ) {

					PrintWriter pw = new PrintWriter( f1 );

					pw.print( difficoltà + "#" + anticipo );

					pw.close();
				}
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

	}

	private Compito[] aggiungiCompito( Compito[] c, Compito compito ) {
		Compito[] clone = new Compito[c.length + 1];

		for ( int i = 0; i < c.length; i++ ) {
			clone[i] = c[i];
		}
		clone[clone.length - 1] = compito;
		return clone;
	}

	public Compito[] leggiCompiti() {

		Compito[] ritorno = new Compito[0];

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

				String[] compitiArr = testoIntero.split( "ª" );
				String[] dati = null;

				for ( int i = 0; i < compitiArr.length; i++ ) {
					dati = compitiArr[i].split( "¦" );
					ritorno = aggiungiCompito( ritorno, new Compito( dati[0], dati[1], dati[2], dati[3], new Double( dati[4] ) ) );
				}
			} catch ( Exception e ) {
			}
		}

		return ritorno;

	}

	public String[] leggiPreferenze() {

		String[] ritorno = new String[0];

		if ( f1.exists() ) {

			char[] in = new char[(int) f1.length()];

			FileReader r;
			BufferedReader b;
			try {
				r = new FileReader( f1 );
				b = new BufferedReader( r );
				b.read( in );
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			} catch ( IOException e ) {
				e.printStackTrace();
			}

			String testoIntero = String.copyValueOf( in );
			ritorno = testoIntero.split( "#" );

		}

		return ritorno;

	}
}
