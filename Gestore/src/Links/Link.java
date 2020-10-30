package Links;

import java.awt.Color;

public class Link {

	private String link;
	private String nome;
	private Color colore;

	public Link( String n, String l, Color c ) {

		link = l;
		nome = n;
		colore = c;
	}

	public String getComponent() {
		return nome + ":\n\n" + link;
	}

	public String getHTMLComponent() {
		return "<html><p><h1>" + nome + "</h1><br/>" + link + "</p></html>";
	}

	public String getLink() {
		return link;
	}

	public void setLink( String link ) {
		this.link = link;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public Color getColore() {
		return colore;
	}

	public void setColore( Color colore ) {
		this.colore = colore;
	}

}
