package ro.ghionoiu.kata;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class RomanNumeralFixture {

    public int romanToDecimal(String romanNumber) {
        Symbol[] symbols = new StringToSymbolsConverter().convert(romanNumber);
        Numeral numeral = new Numeral(symbols);
        return numeral.getValue();
    }
}
