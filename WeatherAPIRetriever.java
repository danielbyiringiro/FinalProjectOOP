import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.json.JSONObject;

public class WeatherAPIRetriever {
    public static final String API_Key = "470fb946a8d245caac2200606230804";
    public static final String API_URL = "http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no";
    private String location;

    public WeatherAPIRetriever(String location){
        this.location = location;
    }
    public String getCurrentWeather() throws IOException{
        String apiUrl = String.format(API_URL,API_Key,location);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String str;
        StringBuffer content = new StringBuffer();

        while ((str=in.readLine()) != null){
            content.append(str);
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }

    public static void writeToFile(String jsonString, WeatherAPIRetriever obj) throws IOException {
        String fileName = obj.location + ".txt";
        JSONObject json = new JSONObject(jsonString);
        JSONObject location = json.getJSONObject("location");
        JSONObject current = json.getJSONObject("current");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("location_name: " + location.getString("name") + "\n");
            writer.write("location_region: " + location.getString("region") + "\n");
            writer.write("location_country: " + location.getString("country") + "\n");
            writer.write("location_latitude: " + location.getDouble("lat") + "\n");
            writer.write("location_longitude: " + location.getDouble("lon") + "\n");
            writer.write("current_last_updated_epoch: " + current.getLong("last_updated_epoch") + "\n");
            writer.write("current_last_updated: " + current.getString("last_updated") + "\n");
            writer.write("current_temperature_celsius: " + current.getDouble("temp_c") + "\n");
            writer.write("current_temperature_fahrenheit: " + current.getDouble("temp_f") + "\n");
            writer.write("current_is_day: " + current.getInt("is_day") + "\n");
            writer.write("current_condition_text: " + current.getJSONObject("condition").getString("text") + "\n");
            writer.write("current_condition_icon: " + current.getJSONObject("condition").getString("icon") + "\n");
            writer.write("current_condition_code: " + current.getJSONObject("condition").getInt("code") + "\n");
            writer.write("current_wind_speed_mph: " + current.getDouble("wind_mph") + "\n");
            writer.write("current_wind_speed_kph: " + current.getDouble("wind_kph") + "\n");
            writer.write("current_wind_direction_degree: " + current.getInt("wind_degree") + "\n");
            writer.write("current_wind_direction: " + current.getString("wind_dir") + "\n");
            writer.write("current_pressure_mb: " + current.getDouble("pressure_mb") + "\n");
            writer.write("current_pressure_in: " + current.getDouble("pressure_in") + "\n");
            writer.write("current_precipitation_mm: " + current.getDouble("precip_mm") + "\n");
            writer.write("current_precipitation_in: " + current.getDouble("precip_in") + "\n");
            writer.write("current_humidity: " + current.getInt("humidity") + "\n");
            writer.write("current_cloud: " + current.getInt("cloud") + "\n");
            writer.write("current_feels_like_celsius: " + current.getDouble("feelslike_c") + "\n");
            writer.write("current_feels_like_fahrenheit: " + current.getDouble("feelslike_f") + "\n");
            writer.write("current_visibility_km: " + current.getDouble("vis_km") + "\n");
            writer.write("current_visibility_miles: " + current.getDouble("vis_miles") + "\n");
            writer.write("current_uv_index: " + current.getDouble("uv") + "\n");
            writer.write("current_wind_gust_mph: " + current.getDouble("gust_mph") + "\n");
            writer.write("current_wind_gust_kph: " + current.getDouble("gust_kph") + "\n");
        }
    }


    public static void main(String[] args) throws IOException{
        WeatherAPIRetriever retriever = new WeatherAPIRetriever("Berekuso");
        String weatherData = retriever.getCurrentWeather();
        writeToFile(weatherData, retriever);
        
    }
}