package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import contabilita.ContabilitaMain;
import gui.Finestra;
import gui.Layout;
import gui.Menu;
import gui.MenuBar;
import links.MainLinks;
import preferenze.PreferenzeGUI;
import utility.Dialog;

public class Main {

	public static void main( String[] args ) {

		PreferenzeGUI gui = new PreferenzeGUI( PreferenzeGUI.TEMA_SCURO );

		MenuBar menuBar = gui.creaMenuBar();
		Layout grid = gui.creaGridLayout( 3, 1 );
		Finestra f = gui.creaFinestra( "Scelta gestore", 400, 500, grid, menuBar, false );

		menuBar.add( gui.creaBottoneCambiaTemaChiaroScuro( "🌙", "☀", true, new PreferenzeGUI( PreferenzeGUI.TEMA_SCURO ),
				new PreferenzeGUI( PreferenzeGUI.TEMA_CHIARO ) ) );
		Menu menu = gui.creaMenu( "Opzioni", null, false );
		menu.add( gui.creaMenuItem( "All'avvio", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				System.out.println( Dialog.mostraDialogComboBox( gui, f, "Scelta gestore all'avvio", "Scegli gestore all'avvio",
						new String[] { "Contabilità", "Homework", "Links" } ) );
			}
		}, true ) );
		menuBar.add( menu );

		grid.add( gui.creaBottone( "Contabilità", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

				ContabilitaMain c = new ContabilitaMain( gui );
				f.dispose();
				c.avvia();
			}
		} ) );
		grid.add( gui.creaBottone( "Homework", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {

			}
		} ) );
		grid.add( gui.creaBottone( "Links", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				MainLinks c = new MainLinks( gui );
				f.dispose();
				c.avvia();
			}
		} ) );

		f.setVisible( true );
	}
}
