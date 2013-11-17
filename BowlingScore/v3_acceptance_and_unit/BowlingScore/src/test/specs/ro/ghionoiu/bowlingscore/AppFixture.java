package ro.ghionoiu.bowlingscore;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class AppFixture {

	public String greetingFor(String firstName) {

		return "Hello " + firstName + "!";
	}
}
