package contabilita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Finestra;
import gui.MenuBar;
import main.Main;
import preferenze.PreferenzeGUI;
import utility.ListaOggettiMenu;

public class ContabilitaMain {

	PreferenzeGUI gui;
	Finestra f;

	public ContabilitaMain( PreferenzeGUI gui ) {
		this.gui = gui;
	}

	public void avvia() {

		f = gui.creaFinestra( "Contabilità", null, creaMenuBar(), false );

		f.setVisible( true );
	}

	private MenuBar creaMenuBar() {

		ListaOggettiMenu menu = new ListaOggettiMenu();
		ListaOggettiMenu submenu = new ListaOggettiMenu();
		submenu.add( "HOME", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		submenu.add( "Gestione importi", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		ListaOggettiMenu submenuTransazioni = new ListaOggettiMenu();
		submenuTransazioni.add( "IN", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		submenuTransazioni.add( "OUT", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		submenu.add( "Transazione", submenuTransazioni );
		submenu.add( "Trasferimento soldi", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		submenu.add( "Mostra movimenti", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {

			}
		} );
		submenu.add( "", gui.creaSeparatore() );
		submenu.add( "Scelta gestore", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				f.dispose();
				Main.main( null );
			}
		} );
		menu.add( "Funzioni", submenu );

		MenuBar menuBar = gui.creaMenuBarDaListaOggettiMenu( menu );

		return menuBar;
	}
}
