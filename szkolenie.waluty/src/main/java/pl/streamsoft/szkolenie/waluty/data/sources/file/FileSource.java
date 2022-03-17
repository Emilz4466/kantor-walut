package pl.streamsoft.szkolenie.waluty.data.sources.file;

import java.math.BigDecimal;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.service.FileService;
import pl.streamsoft.szkolenie.waluty.data.service.ServiceStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public class FileSource implements SourceStrategy {

	private String path;

	private Currency currency;
	private LocalDate date;
	private String format;

	public FileSource(String path, Currency currency, LocalDate date) {
		this.path = path;

		this.currency = currency;
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String getResponse() throws NoDataException {
		ServiceStrategy service = new FileService(path);
		String response = service.getResponse();
		return response;
	}

	@Override
	public BigDecimal getRate() {
		return null;
	}

}
