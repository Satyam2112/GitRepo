import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ReadURL {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://kite.trade/connect/login?api_key=84bxgrli94a2p2la");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		System.out.println(conn.getResponseCode());
		

	}

}
