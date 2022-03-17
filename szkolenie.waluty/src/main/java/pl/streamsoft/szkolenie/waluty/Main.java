package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.streamsoft.szkolenie.waluty.data.ConnectionMethod;
import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.parser.XmlParser;
import pl.streamsoft.szkolenie.waluty.data.sources.api.ApiSource;
import pl.streamsoft.szkolenie.waluty.data.sources.cache.CacheSource;
import pl.streamsoft.szkolenie.waluty.data.sources.file.FileSource;
import pl.streamsoft.szkolenie.waluty.exchanger.CurrencyExchanger;

public class Main {
	public static void main(String[] args) {

		BigDecimal value1 = new BigDecimal(120);
		LocalDate date1 = LocalDate.of(2022, 03, 10);
		LocalDate date2 = LocalDate.of(2001, 03, 13);
		ParserStrategy json = new JsonParser();
		ParserStrategy xml = new XmlParser();

		ApiSource apiSource = new ApiSource(Currency.EUR, date1);
		FileSource fileSource = new FileSource("src/main/java/eur_09-03-2022_json", Currency.EUR, date1);
		CacheSource cacheSource = new CacheSource(Currency.EUR, date1);

		List<ConnectionMethod> connections = new ArrayList<>();

		connections.add(new ConnectionMethod(apiSource, json));
		connections.add(new ConnectionMethod(fileSource, json));
		connections.add(new ConnectionMethod(cacheSource, json));

		CurrencyExchanger exchanger = new CurrencyExchanger();

		List<BigDecimal> newValue1 = exchanger.exchange(connections, value1);

		System.out.println(Arrays.toString(newValue1.toArray()));

	}
}
