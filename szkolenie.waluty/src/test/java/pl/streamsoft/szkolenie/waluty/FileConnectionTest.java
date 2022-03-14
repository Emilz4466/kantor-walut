package pl.streamsoft.szkolenie.waluty;


import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.sources.FileConnection;

public class FileConnectionTest {

	@Test
	public void shouldReturnStringResponse() {
		
		FileConnection fileConnection = new FileConnection("src/main/java/eur_09-03-2022_json");
		
		String response = fileConnection.getResponse();
		
		Assert.assertNotNull(response);
		
	}

}
