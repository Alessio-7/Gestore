package Links;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Bottone;
import main.CampoTesto;
import main.Etichetta;
import main.Finestra;

public class GestoreLink extends JDialog {

	private static final long serialVersionUID = 1L;

	public boolean fineInserimento;

	public static final int AGGIUNGI = 0;
	public static final int MODIFICA = 1;

	Link[] links = new Link[0];

	private void disegnaSchermo() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setLocation( ( (int) screenSize.getWidth() / 2 ) - 230, 500 );

	}

	public void operazione( int tipo, int id ) {

		JPanel pannello = new JPanel();

		fineInserimento = false;

		switch (tipo) {

			case ( AGGIUNGI ): {

				JPanel griglia = new JPanel( new GridLayout( 3, 2 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 175 );
				this.setTitle( "Aggiungi link" );

				Etichetta label = new Etichetta( "Nome : " );
				griglia.add( label );

				CampoTesto nomeTXT = new CampoTesto( "Nome" );
				griglia.add( nomeTXT );

				label = new Etichetta( "Link o percorso file :" );
				griglia.add( label );

				CampoTesto linkTXT = new CampoTesto( "https://" );
				griglia.add( linkTXT );

				label = new Etichetta( "Colore : " );
				griglia.add( label );

				JPanel rgb = new JPanel( new GridLayout( 1, 6 ) );
				rgb.setBackground( Finestra.coloreSfondo );
				rgb.add( new Etichetta( "R :" ) );

				CampoTesto rTXT = new CampoTesto();
				rTXT.setText( "204" );
				rgb.add( rTXT );

				rgb.add( new Etichetta( "G :" ) );

				CampoTesto gTXT = new CampoTesto();
				gTXT.setText( "204" );
				rgb.add( gTXT );

				rgb.add( new Etichetta( "B :" ) );

				CampoTesto bTXT = new CampoTesto();
				bTXT.setText( "204" );
				rgb.add( bTXT );

				griglia.add( rgb );

				Bottone aggiungi = new Bottone( "Aggiungi" );
				pannello.add( aggiungi, BorderLayout.SOUTH );
				aggiungi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						if ( !( linkTXT.getText() == "" || nomeTXT.getText() == "" ) ) {

							aggiungiLink( new Link( nomeTXT.getText(), linkTXT.getText(),
									new Color( new Integer( rTXT.getText() ), new Integer( gTXT.getText() ), new Integer( bTXT.getText() ) ) ) );

							fineInserimento = true;

							dispose();
						}
					}
				} );
				break;
			}
			case ( MODIFICA ): {

				JPanel griglia = new JPanel( new GridLayout( 3, 2 ) );
				griglia.setBackground( Finestra.coloreSfondo );

				pannello.setLayout( new BorderLayout() );
				pannello.add( griglia, BorderLayout.CENTER );

				this.setSize( 500, 175 );
				this.setTitle( "Modifica link" );

				Etichetta label = new Etichetta( "Nome : " );
				griglia.add( label );

				CampoTesto nomeTXT = new CampoTesto( links[id].getNome() );
				griglia.add( nomeTXT );

				label = new Etichetta( "Link o percorso file :" );
				griglia.add( label );

				CampoTesto linkTXT = new CampoTesto( links[id].getLink() );
				griglia.add( linkTXT );

				label = new Etichetta( "Colore : " );
				griglia.add( label );

				JPanel rgb = new JPanel( new GridLayout( 1, 6 ) );
				rgb.setBackground( Finestra.coloreSfondo );
				rgb.add( new Etichetta( "R :" ) );

				CampoTesto rTXT = new CampoTesto();
				rTXT.setText( "" + links[id].getColore().getRed() );
				rgb.add( rTXT );

				rgb.add( new Etichetta( "G :" ) );

				CampoTesto gTXT = new CampoTesto();
				gTXT.setText( "" + links[id].getColore().getGreen() );
				rgb.add( gTXT );

				rgb.add( new Etichetta( "B :" ) );

				CampoTesto bTXT = new CampoTesto();
				bTXT.setText( "" + links[id].getColore().getBlue() );
				rgb.add( bTXT );

				griglia.add( rgb );

				Bottone aggiungi = new Bottone( "Modifica" );
				pannello.add( aggiungi, BorderLayout.SOUTH );
				aggiungi.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent arg0 ) {

						if ( !( linkTXT.getText() == "" || nomeTXT.getText() == "" ) ) {

							links[id] = new Link( nomeTXT.getText(), linkTXT.getText(),
									new Color( new Integer( rTXT.getText() ), new Integer( gTXT.getText() ), new Integer( bTXT.getText() ) ) );

							fineInserimento = true;

							dispose();
						}
					}
				} );
				break;
			}
		}

		this.getContentPane().removeAll();
		this.getContentPane().add( pannello, BorderLayout.CENTER );
	}

	public void aggiungiLink( Link link ) {

		Link[] clone = new Link[links.length + 1];

		for ( int i = 0; i < links.length; i++ ) {
			clone[i] = links[i];
		}
		clone[clone.length - 1] = link;

		links = clone;

	}

	public void rimuoviLink( int index ) {
		Link[] anotherArray = new Link[links.length - 1];

		for ( int i = 0, k = 0; i < links.length; i++ ) {

			if ( i == index ) {
				continue;
			}

			anotherArray[k++] = links[i];
		}

		links = anotherArray;
	}

	public Color[] coloriLinks() {
		Color[] colori = new Color[links.length];

		for ( int i = 0; i < colori.length; i++ ) {

			colori[i] = links[i].getColore();
		}

		return colori;
	}

	public GestoreLink( JFrame frame ) {

		super( frame, "", true );
		disegnaSchermo();
	}
}
