package Contabilita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lettore {

	File f = new File( System.getProperty( "user.home" ) + "\\Documents\\Gestore\\contabilita\\dati.txt" );

	TotParziale[] parziali = new TotParziale[0];
	String[] movimenti = new String[0];

	public void salvaDati( TotParziale[] parziali, String[] movimenti ) throws IOException {

		if ( f.exists() ) {

			PrintWriter pw = new PrintWriter( f );

			if ( parziali.length > 0 ) {
				pw.print( parziali.length );
				pw.append( '#' );
				for ( int i = 0; i < parziali.length; i++ ) {
					pw.append( i + "," + parziali[i].getNome() + ":" + parziali[i].getSoldi() + ";" );
				}
				pw.append( "@" );
				pw.print( movimenti.length );
				pw.append( "&" );
				for ( int i = 0; i < movimenti.length; i++ ) {
					pw.append( movimenti[i] + ";" );
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

				if ( parziali.length > 0 ) {
					pw.print( parziali.length );
					pw.append( '#' );
					for ( int i = 0; i < parziali.length; i++ ) {
						pw.append( i + "," + parziali[i].getNome() + ":" + parziali[i].getSoldi() + ";" );
					}
					pw.append( "@" );
					pw.print( movimenti.length );
					pw.append( "&" );
					for ( int i = 0; i < movimenti.length; i++ ) {
						pw.append( movimenti[i] + ";" );
					}
				} else {
					pw.print( "n" );
				}

				pw.close();
			}

		}
	}

	public TotParziale[] leggiDatiParziali( TotParziale[] parzialiArray ) throws IOException {

		int volte;
		String testoIntero;
		String importi;
		String nome;
		double soldi;
		int c = 0;

		char[] in = new char[(int) f.length()];
		FileReader r = new FileReader( f );
		BufferedReader b = new BufferedReader( r );
		b.read( in );

		testoIntero = String.copyValueOf( in );

		volte = new Integer( testoIntero.substring( 0, testoIntero.indexOf( '#' ) ) );
		importi = testoIntero.substring( testoIntero.indexOf( '#' ) + 1, ( testoIntero.indexOf( "@" ) ) );
		parziali = new TotParziale[0];

		for ( int i = 0; i < volte; i++ ) {

			c = 0;

			c += 2;

			nome = importi.substring( c, ( importi.indexOf( ":" ) ) );
			c = importi.indexOf( ":" ) + 1;

			soldi = new Double( importi.substring( c, ( importi.indexOf( ";" ) ) ) );
			c = importi.indexOf( ";" );

			importi = importi.substring( c + 1, importi.length() );

			aggiungiArrayParziali();
			TotParziale parziale = new TotParziale( nome, soldi );
			parziali[i] = parziale;

		}

		b.close();

		return parziali;

	}

	public String[] leggiDatiMovimenti( String[] movimentiArray ) throws IOException {

		movimenti = new String[0];

		int volte;

		String testoIntero;
		String mov;
		String movimento1;

		char[] in = new char[(int) f.length()];
		FileReader r = new FileReader( f );
		BufferedReader b = new BufferedReader( r );
		b.read( in );
		testoIntero = String.copyValueOf( in );

		volte = new Integer( testoIntero.substring( ( testoIntero.indexOf( '@' ) + 1 ), testoIntero.indexOf( '&' ) ) );

		mov = testoIntero.substring( ( testoIntero.indexOf( "&" ) + 1 ) );

		for ( int i = 0; i < volte; i++ ) {

			movimento1 = mov.substring( 0, ( mov.indexOf( ";" ) ) );

			aggiungiArrayMovimenti();
			movimenti[i] = movimento1;

			mov = mov.substring( ( mov.indexOf( ";" ) + 1 ), mov.length() );

		}

		b.close();

		return movimenti;

	}

	public void aggiungiArrayParziali() {

		TotParziale[] array = parziali;
		parziali = new TotParziale[parziali.length + 1];
		for ( int i = 0; i < ( parziali.length - 1 ); i++ ) {
			parziali[i] = array[i];
		}
	}

	public void aggiungiArrayMovimenti() {

		String[] array = movimenti;
		movimenti = new String[movimenti.length + 1];
		for ( int i = 0; i < ( movimenti.length - 1 ); i++ ) {
			movimenti[i] = array[i];
		}
	}

	public long testo() {

		long testo = f.length();
		return testo;
	}

}
