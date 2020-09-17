package jacchm.footballapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExternalFootballAPI {

    private final String FOOTBALL_API_URL = "http://api.football-data.org";

    private final String AUTH_TOKEN = "641dd694283241309d4549a804a9757e";
    private final String AUTH_HEADER = "X-Auth-Token";

    private final Logger logger = LoggerFactory.getLogger(ExternalFootballAPI.class);

    public String getCompetitions() throws IOException {
        String url = FOOTBALL_API_URL + "/v2/competitions/";
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // setting for HttpURLConnection
        con.setRequestMethod("GET");
        con.setRequestProperty(AUTH_HEADER, AUTH_TOKEN);

        // logs from request
        logger.info("Update competitions request has been sent.");

        // handle error response code if occurs
        int responseCode = con.getResponseCode();
        logger.info("Response code: " + responseCode);
        
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = con.getInputStream();
        } else {
            inputStream = con.getErrorStream();
        }

        // reading response and building respond
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null)
            response.append(currentLine);

        bufferedReader.close();

        return response.toString();
    }

/*    private String connectToFootballAPI(URL url, ){

    }*/

}
