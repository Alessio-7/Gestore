package main;

import javax.swing.JTextField;

public class CampoTesto extends JTextField {

	private static final long serialVersionUID = 1L;

	public CampoTesto() {
		setFont( Etichetta.font );
	}

	public CampoTesto( String s ) {
		super( s );
		setFont( Etichetta.font );
	}

}
