package main;

import javax.swing.JComboBox;

public class ComboBox extends JComboBox<Object> {

	private static final long serialVersionUID = 1L;

	public ComboBox( String[] s ) {
		super( s );

		setFont( Etichetta.font );
	}

	public ComboBox( Integer[] i ) {
		super( i );

		setFont( Etichetta.font );
	}

}
