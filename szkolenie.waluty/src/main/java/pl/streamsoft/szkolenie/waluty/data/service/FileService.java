package pl.streamsoft.szkolenie.waluty.data.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;

public class FileService implements ServiceStrategy {

	private String path;

	public FileService() {
	}

	public FileService(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getResponse() throws NoDataException {

		String response = "";

		try {
			response = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			throw new NoDataException("Brak danych pod wskazaną datą!", e);
		}

		return response;
	}

}
