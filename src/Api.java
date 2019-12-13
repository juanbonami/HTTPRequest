import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import sun.net.www.http.HttpClient;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import static java.util.logging.Level.parse;


public class Api {

    private static HttpURLConnection connection;

    public void executeAPI(String movieString) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String urlString = "http://www.omdbapi.com/?apikey=8e2e09a4&t=";
        urlString = urlString + formatMovieString(movieString);

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // request set up
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            //System.out.println(status);
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

                parse(responseContent.toString());

            }
            //System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


    }

    public String formatMovieString(String title) {
        char[] charTitle = title.toCharArray();
        String formattedTitle = "";
        for (int i = 0; i < charTitle.length; i++) {
            if (charTitle[i] == ' ') {
                formattedTitle = formattedTitle + "+";
            } else {
                formattedTitle = formattedTitle + charTitle[i];
            }

        }

        return formattedTitle;
    }
        String title;
        String year;
        String rated;
        String released;
        String runtime;
        String genre;
        String director;
        String actors;
        String plot;
        String details;
        String ignore;

    public void parse(String responseBody) {
        JSONObject movie = new JSONObject(responseBody);
        title = movie.getString("Title");
        year = movie.getString("Year");
         rated = movie.getString("Rated");
         released = movie.getString("Released");
         runtime = movie.getString("Runtime");
         genre = movie.getString("Genre");
         director = movie.getString("Director");
         actors = movie.getString("Actors");
         plot = movie.getString("Plot");

         details = "Title: " + title + "\nYear: " + year + "\nRated: " + rated + "\nReleased: " + released +
                "\nRuntime: " + runtime + "\nGenre: " + genre + "\nDirector: " + director + "\nActors: " + actors +
                "\nPlot: " + plot;



    }

}


