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
	 * Point de coupe qui intercepte les appels � la m�thode println
	 * 
	 * @param message
	 *            le texte en param�tre de la m�thode println
	 */
	pointcut affichage(Object message) : args(message) && call(void java.io.PrintStream.println(*));

	/**
	 * Code advice contenant le code � ex�cuter lorsque l'aspect est d�clench�
	 * 
	 * @param message
	 *            le message � afficher
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