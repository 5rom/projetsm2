package logging;

import java.io.FileWriter;

public aspect ALog {
	FileWriter logFile = null;

	/**
	 * Constructeur de l'aspect : initialise le fichier de log
	 */
	public ALog() {
		try {
			logFile = new FileWriter("log.txt", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Point de coupe qui intercepte les appels à la méthode println
	 * 
	 * @param message
	 *            le texte en paramètre de la méthode println
	 */
	pointcut affichage(Object message) : args(message) && call(void java.io.PrintStream.println(*));

	/**
	 * Code advice contenant le code à exécuter lorsque l'aspect est déclenché
	 * 
	 * @param message
	 *            le message à afficher
	 */
	after(Object message) : affichage(message) {
		try {
			logFile.write(new java.util.Date().toString() + " : "
					+ message.toString());
			logFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}