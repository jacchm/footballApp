package jacchm.footballapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jacchm.footballapp.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExternalFootballAPIService {

    private final String FOOTBALL_API_URL = "http://api.football-data.org";
    private final String httpMethod = "GET";

    private final String AUTH_TOKEN = "641dd694283241309d4549a804a9757e";
    private final String AUTH_HEADER = "X-Auth-Token";

    private final Logger logger = LoggerFactory.getLogger(ExternalFootballAPIService.class);

    public String getCompetitions() throws IOException {
        String url = FOOTBALL_API_URL + "/v2/competitions/";
        URL obj = new URL(url);

        String fileName = "AllCompetitions";

        return getResponse(obj, fileName);
    }

    public String getTeams(int competitionId) throws IOException {
        String url = FOOTBALL_API_URL + "/v2/competitions/" + competitionId + "/teams";
        URL obj = new URL(url);

        String fileName = competitionId + "Teams";

        return getResponse(obj, fileName);
    }

    public String getStandings(int competitionId) throws IOException {
        String url = FOOTBALL_API_URL + "/v2/competitions/" + competitionId + "/standings";
        logger.info(url + " request has been sent.");
        URL obj = new URL(url);

        String fileName = competitionId + "Standings";
        return getResponse(obj, fileName);
    }

    private String getResponse(URL obj, String fileName) throws IOException {

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // setting for HttpURLConnection
        con.setRequestMethod(httpMethod);
        con.setRequestProperty(AUTH_HEADER, AUTH_TOKEN);

        // logs from request
        logger.info("Request to external API has been sent.");

        // handle error response code if occurs
        int responseCode = con.getResponseCode();
        logger.info("Response code: " + responseCode);

        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = con.getInputStream();
        } else {
            throw new IOException();
        }

        // reading response and building respond string
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null)
            response.append(currentLine);
        bufferedReader.close();

        // saving the response to file
        saveToFile(response.toString(), fileName);
        logger.info("Backup file has been done.");

        return response.toString();
    }

    private void saveToFile(String jsonInput, String fileName) throws FileNotFoundException {

        PrintWriter out = new PrintWriter("src/main/resources/dataFromExternalApi/" + fileName + ".json");
        try {
            out.print(JsonUtil.prettyPrint(JsonUtil.toJson(JsonUtil.parse(jsonInput))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        out.close();
    }

}
