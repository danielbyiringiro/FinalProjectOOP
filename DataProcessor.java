import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class DataProcessor {
    private String locationName;
    private String locationRegion;
    private String locationCountry;
    public double temperatureCelsius;
    public double temperatureFahrenheit;
    public double windSpeedMph;
    public double humidity;
    public String windDirection;
    public String isRaining;

    public DataProcessor(WeatherAPIRetriever obj) {
        String filePath = obj.getLocation() + ".txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();
                switch (key) {
                    case "location_name":
                        locationName = value;
                        break;
                    case "location_region":
                        locationRegion = value;
                        break;
                    case "location_country":
                        locationCountry = value;
                        break;
                    case "current_temperature_celsius":
                        temperatureCelsius = Double.parseDouble(value);
                        break;
                    case "current_temperature_fahrenheit":
                        temperatureFahrenheit = Double.parseDouble(value);
                        break;
                    case "current_wind_speed_mph":
                        windSpeedMph = Double.parseDouble(value);
                        break;
                    case "current_humidity":
                        humidity = Double.parseDouble(value);
                        break;
                    case "current_wind_direction":
                        windDirection = value;
                        break;
                    case "is_raining":
                        isRaining = value;
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public double calculateWindChillFahrenheit() {
        double windChill = ClimateConstants.WC_A + (ClimateConstants.WC_B * temperatureFahrenheit) - (ClimateConstants.WC_C * Math.pow(windSpeedMph, ClimateConstants.WC_E)) + (ClimateConstants.WC_D * temperatureFahrenheit * Math.pow(windSpeedMph, ClimateConstants.WC_E));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(windChill);
        return Double.parseDouble(formattedNumber);
    }

    public double calculateWindChillCelsius() {
        double windChill = ClimateConstants.WC_A + (ClimateConstants.WC_B * temperatureCelsius) - (ClimateConstants.WC_C * Math.pow(windSpeedMph, ClimateConstants.WC_E)) + (ClimateConstants.WC_D * temperatureCelsius * Math.pow(windSpeedMph, ClimateConstants.WC_E));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(windChill);
        return Double.parseDouble(formattedNumber);
    }

    public double calculateDewPointCelsius() {
        double dewPoint = (ClimateConstants.DP_B * (Math.log(humidity/100)+ (ClimateConstants.DP_A * temperatureCelsius)/(ClimateConstants.DP_B + temperatureCelsius)))/(ClimateConstants.DP_A - (Math.log(humidity/100)+ (ClimateConstants.DP_A * temperatureCelsius)/(ClimateConstants.DP_B + temperatureCelsius)));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(dewPoint);
        return Double.parseDouble(formattedNumber);
    }

    public double calculateDewPointFahrenheit() {
        double dewPoint = (ClimateConstants.DP_B * (Math.log(humidity/100)+ (ClimateConstants.DP_A * temperatureFahrenheit)/(ClimateConstants.DP_B + temperatureFahrenheit)))/(ClimateConstants.DP_A - (Math.log(humidity/100)+ (ClimateConstants.DP_A * temperatureFahrenheit)/(ClimateConstants.DP_B + temperatureFahrenheit)));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(dewPoint);
        return Double.parseDouble(formattedNumber);
    }

    public double calculateHeatIndexCelsius() {
        double heatIndex = ClimateConstants.HI_A + (ClimateConstants.HI_B * temperatureCelsius) + (ClimateConstants.HI_C * humidity) + (ClimateConstants.HI_D * temperatureCelsius * humidity) + (ClimateConstants.HI_E * Math.pow(temperatureCelsius, 2)) + (ClimateConstants.HI_F * Math.pow(humidity, 2)) + (ClimateConstants.HI_G * Math.pow(temperatureCelsius, 2) * humidity) + (ClimateConstants.HI_H * temperatureCelsius * Math.pow(humidity, 2)) + (ClimateConstants.HI_I * Math.pow(temperatureCelsius, 2) * Math.pow(humidity, 2));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(heatIndex);
        return Double.parseDouble(formattedNumber);
        
    }

    public double calculateHeatIndexFahrenheit(){
        double heatIndex = ClimateConstants.HI_A + (ClimateConstants.HI_B * temperatureFahrenheit) + (ClimateConstants.HI_C * humidity) + (ClimateConstants.HI_D * temperatureFahrenheit * humidity) + (ClimateConstants.HI_E * Math.pow(temperatureFahrenheit, 2)) + (ClimateConstants.HI_F * Math.pow(humidity, 2)) + (ClimateConstants.HI_G * Math.pow(temperatureFahrenheit, 2) * humidity) + (ClimateConstants.HI_H * temperatureFahrenheit * Math.pow(humidity, 2)) + (ClimateConstants.HI_I * Math.pow(temperatureFahrenheit, 2) * Math.pow(humidity, 2));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(heatIndex);
        return Double.parseDouble(formattedNumber);
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public double getWindSpeedMph() {
        return windSpeedMph;
    }

    public String getWindDirection() {
        return windDirection;
    }
}