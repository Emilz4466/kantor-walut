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

	public FileSource(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrency(Currency currency) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDate(LocalDate date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFormat(String format) {
		// TODO Auto-generated method stub

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
