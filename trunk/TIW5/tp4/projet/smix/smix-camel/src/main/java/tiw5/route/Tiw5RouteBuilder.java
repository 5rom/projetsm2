package tiw5.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Router
 */
public class Tiw5RouteBuilder extends RouteBuilder {

	public void configure() {

		// de la queue JMS au fichier avec log au milieu
		from("activemq:tiw5-test")
				.to("log:tiw5.apresJMS?level=INFO")
				.to("jbi:endpoint:http://master-info.univ-lyon1.fr/M2TI/TIW5/su/ecrivain/endpoint");
	}
}
