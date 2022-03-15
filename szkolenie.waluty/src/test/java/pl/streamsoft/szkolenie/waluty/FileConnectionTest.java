package pl.streamsoft.szkolenie.waluty;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.sources.FileConnection;

public class FileConnectionTest {

	@Test
	public void shouldReturnStringResponse() {

		// given
		FileConnection fileConnection = new FileConnection("src/test/java/test_file");

		// when
		String response = fileConnection.getResponse();

		// then
		Assert.assertNotNull(response);

	}

}
