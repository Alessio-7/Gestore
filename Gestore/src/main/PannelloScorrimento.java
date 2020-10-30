package main;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class PannelloScorrimento extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public PannelloScorrimento( JPanel p ) {
		super( p );
		setBorder( BorderFactory.createEmptyBorder() );
		setBackground( Finestra.coloreSfondo );
		getHorizontalScrollBar().setUnitIncrement( 20 );
		getHorizontalScrollBar().setUI( new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Finestra.coloreMenu;
				this.trackColor = Finestra.coloreSfondo;
			}

			@Override
			protected JButton createDecreaseButton( int orientation ) {
				JButton button = super.createDecreaseButton( orientation );
				button.setBackground( Bottone.sfondo );
				button.setForeground( Bottone.foreground );
				button.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) ) );
				return button;
			}

			@Override
			protected JButton createIncreaseButton( int orientation ) {
				JButton button = super.createIncreaseButton( orientation );
				button.setBackground( Bottone.sfondo );
				button.setForeground( Bottone.foreground );
				button.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) ) );
				return button;
			}
		} );
		getVerticalScrollBar().setUnitIncrement( 20 );
		getVerticalScrollBar().setUI( new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Finestra.coloreMenu;
				this.trackColor = Finestra.coloreSfondo;
			}

			@Override
			protected JButton createDecreaseButton( int orientation ) {
				JButton button = super.createDecreaseButton( orientation );
				button.setBackground( Bottone.sfondo );
				button.setForeground( Bottone.foreground );
				button.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) ) );
				return button;
			}

			@Override
			protected JButton createIncreaseButton( int orientation ) {
				JButton button = super.createIncreaseButton( orientation );
				button.setBackground( Bottone.sfondo );
				button.setForeground( Bottone.foreground );
				button.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Menu.bordo ), BorderFactory.createEmptyBorder( 5, 15, 5, 15 ) ) );
				return button;
			}
		} );
	}

}
