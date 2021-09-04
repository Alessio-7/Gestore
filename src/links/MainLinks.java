package links;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import gui.Bottone;
import gui.Finestra;
import gui.Label;
import gui.Layout;
import gui.MenuBar;
import gui.MenuItem;
import gui.TextField;
import main.Main;
import main.WrapLayout;
import preferenze.Fonts;
import preferenze.PreferenzeGUI;
import utility.ColorChooser;
import utility.Dialog;
import utility.ListaOggettiMenu;

public class MainLinks {

	private PreferenzeGUI gui;
	private Finestra f;
	private Layout grid;

	private ArrayList<Link> links;

	public MainLinks( PreferenzeGUI gui ) {
		this.gui = gui;
	}

	public void avvia() {

		links = new ArrayList<Link>();
		links.add( new Link( "Ciao", "F:\\Computer\\Documenti\\java-workspace", Color.red ) );
		links.add( new Link( "rob", "F:\\Computer\\Documenti\\java-workspace", Color.blue ) );

		links.add( new Link( "asfasf", "F:\\Computer\\Documenti\\java-workspace", Color.green ) );
		links.add( new Link( "af", "F:\\Computer\\Documenti\\java-workspace", Color.yellow ) );

		grid = gui.creaGridBagLayout();
		grid.setLayout( new WrapLayout( WrapLayout.CENTER, 10, 10 ) );
		f = gui.creaFinestra( "Links", grid, creaMenuBar(), false );

		home();

		f.setVisible( true );
	}

	private MenuBar creaMenuBar() {

		ListaOggettiMenu menu = new ListaOggettiMenu();

		ListaOggettiMenu submenu = new ListaOggettiMenu();
		submenu.add( "HOME", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				home();
			}
		} );
		submenu.add( "Gestione links", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				gestioneLinks();
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

		return gui.creaMenuBarDaListaOggettiMenu( menu );
	}

	private void home() {
		grid.removeAll();
		grid.revalidate();
		grid.repaint();

		Iterator<Link> i = links.iterator();
		while ( i.hasNext() ) {
			grid.add( new BottoneLink( gui, i.next() ) );
		}
	}

	private void gestioneLinks() {
		grid.removeAll();
		grid.revalidate();
		grid.repaint();

		Iterator<Link> i = links.iterator();
		while ( i.hasNext() ) {
			Link l = i.next();

			Layout g = gui.creaGridLayout( 2, 1 );
			( ( GridLayout ) g.getLayout() ).setHgap( 10 );
			( ( GridLayout ) g.getLayout() ).setVgap( 10 );

			Layout gBottoni = gui.creaGridLayout( 2, 2 );
			( ( GridLayout ) gBottoni.getLayout() ).setHgap( 5 );
			( ( GridLayout ) gBottoni.getLayout() ).setVgap( 5 );

			g.setBorder( gui.bordi.bordoGenericoFocus( 2, new Insets( 10, 10, 10, 10 ), l.getColore() ) );
			g.add( gui.creaLabel( "<html><h1>" + l.getTitolo() + "</h1><br>" + l.getLink() + "</html>" ) );

			Bottone copia = gui.creaBottone( "Copia", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {

					StringSelection stringSelection = new StringSelection( l.getLink() );
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents( stringSelection, null );
				}
			} );
			Bottone apri = gui.creaBottone( "Apri", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {

					try {
						Runtime.getRuntime().exec( "explorer.exe /open," + l.getLink() );
					} catch ( IOException ex ) {
					}
				}
			} );
			Bottone modifica = gui.creaBottone( "Modifica", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {

					Link nuovoLink = DialogLink.modificaLink( gui, f, l );
					if ( nuovoLink != null ) {
						links.set( links.indexOf( l ), nuovoLink );
						gestioneLinks();
					}
				}
			} );
			Bottone elimina = gui.creaBottone( "Elimina", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {

					links.remove( links.indexOf( l ) );
					gestioneLinks();
				}
			} );
			gBottoni.add( copia );
			gBottoni.add( apri );
			gBottoni.add( modifica );
			gBottoni.add( elimina );

			g.add( gBottoni );

			grid.add( g );
		}
		Bottone aggiungi = gui.creaBottone( " + ", new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				links.add( DialogLink.creaLink( gui, f ) );
				gestioneLinks();
			}
		} );
		aggiungi.setFont( new Font( "Microsoft New Tai Lue", Fonts.PLAIN, 50 ) );
		grid.add( aggiungi );
	}

	private class BottoneLink extends Bottone {

		private static final long serialVersionUID = 1L;

		public BottoneLink( PreferenzeGUI gui, Link link ) {
			super( link.testoHTML(), gui.colori.interagibile(), gui.colori.testo(), gui.fonts.fontInteragibile( Fonts.BOLD ), link.getBordo(),
					link.getBordoFocus(), new ActionListener() {

						@Override
						public void actionPerformed( ActionEvent e ) {

							try {
								Runtime.getRuntime().exec( "explorer.exe /open," + link.getLink() );
							} catch ( IOException ex ) {
							}
						}
					} );
			addMouseListener( new AdattatoreMouse( link ) );
		}
	}

	class AdattatoreMouse extends MouseAdapter {
		private JPopupMenu popup = new JPopupMenu();

		public AdattatoreMouse( Link link ) {

			MenuItem menuItem = gui.creaMenuItem( "Copia", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					StringSelection stringSelection = new StringSelection( link.getLink() );
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents( stringSelection, null );
				}
			}, true );
			popup.add( menuItem );

			menuItem = gui.creaMenuItem( "Apri", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					try {
						Runtime.getRuntime().exec( "explorer.exe /open," + link.getLink() );
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				}
			}, true );
			popup.add( menuItem );

			popup.add( gui.creaSeparatore() );

			menuItem = gui.creaMenuItem( "Modifica", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					Link nuovoLink = DialogLink.modificaLink( gui, f, link );
					if ( nuovoLink != null ) {
						links.set( links.indexOf( link ), nuovoLink );
						home();
					}
				}
			}, true );
			popup.add( menuItem );

			popup.setBorderPainted( false );
			popup.setBackground( menuItem.getBackground() );

		}

		public void mouseReleased( MouseEvent me ) {
			showPopup( me ); // showPopup() is our own user-defined method
		}

		void showPopup( MouseEvent me ) {
			if ( me.isPopupTrigger() )
				popup.show( me.getComponent(), me.getX(), me.getY() );
		}
	}

	private class DialogLink {

		private static boolean scelta;

		public static Link creaLink( PreferenzeGUI gui, Frame finestra ) {

			Link ritorno = null;

			Layout grid = gui.creaGridBagLayout();

			Layout panelBottoni = gui.creaGridBagLayout();
			Dialog d = gui.creaDialog( finestra, "Modifica Link", true, 400, 250, grid, panelBottoni, false );
			d.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
			panelBottoni = gui.creaPanelBottoni( new String[] { "Ok", "Cancella" }, new ActionListener[] { new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					scelta = true;
					d.dispose();
				}
			}, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					d.dispose();
				}
			} } );
			d.add( panelBottoni, BorderLayout.SOUTH );

			scelta = false;

			TextField titolo = gui.creaTextField( "Titolo Link" );
			titolo.setColumns( titolo.getColumns() + 3 );
			grid.add( gui.creaLabel( "Titolo:" ),
					new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( titolo,
					new GridBagConstraints( 1, 0, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			TextField testoLink = gui.creaTextField( "https://" );
			testoLink.setColumns( 31 );
			grid.add( gui.creaLabel( "Link:" ),
					new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( testoLink,
					new GridBagConstraints( 1, 1, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			grid.add( gui.creaLabel( "Colore:" ),
					new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			Label colore = new Label( "BBBBBBB", Color.gray, Color.gray, gui.fonts.fontGenerico( Fonts.PLAIN ), gui.bordi.bordoGenerico() );
			grid.add( colore,
					new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( gui.creaBottone( "Cambia colore", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					Color coloreNuovo = ColorChooser.mostraDialogoColore( d, "Scelta colore Link", colore.getBackground() );
					colore.setBackground( coloreNuovo );
					colore.setForeground( coloreNuovo );
				}
			} ), new GridBagConstraints( 2, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			d.setVisible( true );

			while ( d.isDisplayable() ) {
			}

			if ( scelta ) {
				ritorno = new Link( titolo.getText(), testoLink.getText(), colore.getBackground() );
			}

			return ritorno;
		}

		public static Link modificaLink( PreferenzeGUI gui, Frame finestra, Link link ) {

			Link ritorno = null;

			Layout grid = gui.creaGridBagLayout();

			Layout panelBottoni = gui.creaGridBagLayout();
			Dialog d = gui.creaDialog( finestra, "Modifica Link", true, 400, 250, grid, panelBottoni, false );
			d.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
			panelBottoni = gui.creaPanelBottoni( new String[] { "Ok", "Cancella" }, new ActionListener[] { new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					scelta = true;
					d.dispose();
				}
			}, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent arg0 ) {
					d.dispose();
				}
			} } );
			d.add( panelBottoni, BorderLayout.SOUTH );

			scelta = false;

			TextField titolo = gui.creaTextField( link.getTitolo() );
			titolo.setColumns( titolo.getColumns() + 3 );
			grid.add( gui.creaLabel( "Titolo:" ),
					new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( titolo,
					new GridBagConstraints( 1, 0, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			TextField testoLink = gui.creaTextField( link.getLink() );
			testoLink.setColumns( 31 );
			grid.add( gui.creaLabel( "Link:" ),
					new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( testoLink,
					new GridBagConstraints( 1, 1, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			grid.add( gui.creaLabel( "Colore:" ),
					new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			Label colore = new Label( "BBBBBBB", link.getColore(), link.getColore(), gui.fonts.fontGenerico( Fonts.PLAIN ), gui.bordi.bordoGenerico() );
			grid.add( colore,
					new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );
			grid.add( gui.creaBottone( "Cambia colore", new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					Color coloreNuovo = ColorChooser.mostraDialogoColore( d, "Scelta colore Link", colore.getBackground() );
					colore.setBackground( coloreNuovo );
					colore.setForeground( coloreNuovo );
				}
			} ), new GridBagConstraints( 2, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

			d.setVisible( true );

			while ( d.isDisplayable() ) {
			}

			if ( scelta ) {
				ritorno = new Link( titolo.getText(), testoLink.getText(), colore.getBackground() );
			}

			return ritorno;
		}

	}

}

class Link {
	private String titolo;
	private String link;
	private Color colore;

	public Link( String titolo, String link, Color colore ) {
		this.titolo = titolo;
		this.link = link;
		this.colore = colore;
	}

	public Border getBordo() {
		return BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( colore, 3 ), BorderFactory.createEmptyBorder( 0, 20, 20, 20 ) );
	}

	public Border getBordoFocus() {
		return BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder( new Color( ( 255 - colore.getRed() ), ( 255 - colore.getGreen() ), ( 255 - colore.getBlue() ) ), 3 ),
				BorderFactory.createEmptyBorder( 0, 20, 20, 20 ) );
	}

	public String testoHTML() {
		return "<html><p><h1>" + titolo + "</h1><br/>" + link + "</p></html>";
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo( String titolo ) {
		this.titolo = titolo;
	}

	public String getLink() {
		return link;
	}

	public void setLink( String link ) {
		this.link = link;
	}

	public Color getColore() {
		return colore;
	}

	public void setColore( Color colore ) {
		this.colore = colore;
	}
}
