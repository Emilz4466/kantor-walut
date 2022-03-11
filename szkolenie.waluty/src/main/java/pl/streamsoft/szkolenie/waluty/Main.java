package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.exchanger.CurrencyExchanger;

public class Main {
	public static void main(String[] args) {
		BigDecimal value1 = new BigDecimal(120); 
		LocalDate date1 = LocalDate.of(2022, 3, 9);
		JsonParser json = new JsonParser();
		
		CurrencyExchanger exchanger = new CurrencyExchanger();
		
		BigDecimal newValue1 = exchanger.exchange(Currency.EUR, date1, value1, json);
		BigDecimal newValue2 = exchanger.exchange("src/main/java/eur_09-03-2022_json", value1, json);
		
		System.out.println(newValue1);
		System.out.println(newValue2);
	}
}
