package pl.streamsoft.szkolenie.waluty.data.sources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileConnection implements ResponseStrategy{

	private String path;
	
	public FileConnection(String path) {
		this.path = path;
	}
	
	@Override
	public String getResponse() {
		

        String response = "";
 
        try {
            response = new String ( Files.readAllBytes( Paths.get(path) ) );
        } catch (IOException e) {
           response = null;
        }
 
        return response;
	}
	
}
