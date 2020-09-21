package Homework;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class Notifica {

	public static final MessageType INFO = MessageType.INFO;
	public static final MessageType AVVISO = MessageType.WARNING;
	public static final MessageType ERRORE = MessageType.ERROR;

	public static void notifica( String titolo, String messaggio, MessageType tipo ) {

		try {

			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage( "some-icon.png" );
			TrayIcon trayIcon = new TrayIcon( image, "Homework" );

			trayIcon.setImageAutoSize( true );
			trayIcon.setToolTip( "System tray icon demo" );
			tray.add( trayIcon );

			trayIcon.displayMessage( titolo, messaggio, tipo );

		} catch ( Exception ex ) {
			System.err.print( ex );
		}
	}
}
