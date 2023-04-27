import java.io.IOException;

public class ReportGenerator {
    private DataProcessor dataProcessor;

    public ReportGenerator(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public String generateReport() {
        String locationName = dataProcessor.getLocationName();
        String locationRegion = dataProcessor.getLocationRegion();
        String locationCountry = dataProcessor.getLocationCountry();
        double temperatureCelsius = dataProcessor.getTemperatureCelsius();
        double temperatureFahrenheit = dataProcessor.getTemperatureFahrenheit();
        double humidity = dataProcessor.getHumidity();
        double windSpeed = dataProcessor.getWindSpeedMph();

        double chillingPointCelsius = dataProcessor.calculateWindChillCelsius();
        double chillingPointFahrenheit = dataProcessor.calculateWindChillFahrenheit();
        double dewPointCelsius = dataProcessor.calculateDewPointCelsius();
        double dewPointFahrenheit = dataProcessor.calculateDewPointFahrenheit();
        double heatIndexCelsius = dataProcessor.calculateHeatIndexCelsius();
        double heatIndexFahrenheit = dataProcessor.calculateHeatIndexFahrenheit();
        

        String report = String.format("\nWeather Report for %s:\n",locationName );
        report += "\n";
        report += String.format("Location: %s, %s, %s\n", locationName, locationRegion, locationCountry);
        report += String.format("Temperature Celsius: %.1f °C\n", temperatureCelsius);
        report += String.format("Temperature Fahrenheit: %.1f °F\n", temperatureFahrenheit);
        report += String.format("Wind Chill Celsius: %.1f °C\n", chillingPointCelsius);
        report += String.format("Wind Chill Fahrenheit: %.1f °F\n", chillingPointFahrenheit);
        report += String.format("Dew Point Celsius: %.1f °C\n", dewPointCelsius);
        report += String.format("Dew Point Fahrenheit: %.1f °F\n", dewPointFahrenheit);
        report += String.format("Heat Index Celsius: %.1f °C\n", heatIndexCelsius);
        report += String.format("Heat Index Fahrenheit: %.1f °F\n", heatIndexFahrenheit);
        report += String.format("Humidity: %.1f %%\n", humidity);
        report += String.format("Wind Speed: %.1f km/h\n", windSpeed);
        report += String.format("Wind Direction: %s\n", dataProcessor.getWindDirection());
        return report;
    }

    public static void main(String[] args) throws IOException {
        WeatherAPIRetriever berekusoApiRetriever = new WeatherAPIRetriever("Kigali");
        DataProcessor dataProcessor = new DataProcessor(berekusoApiRetriever);
        WeatherAPIRetriever.writeToFileCurrent(berekusoApiRetriever.getCurrentWeather(),berekusoApiRetriever);
        ReportGenerator reportGenerator = new ReportGenerator(dataProcessor);
        String report = reportGenerator.generateReport();
        System.out.println(report);
    }
}
