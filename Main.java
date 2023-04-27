import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WeatherAPIRetriever berekusoApiRetriever = new WeatherAPIRetriever("Berekuso");
        String toString = berekusoApiRetriever.getCurrentWeather();
        WeatherAPIRetriever.writeToFileCurrent(toString, berekusoApiRetriever);
        DataProcessor berekusoDataProcessor = new DataProcessor(berekusoApiRetriever);
        String hist = berekusoApiRetriever.getHistoricalWeather(berekusoApiRetriever);
        berekusoApiRetriever.parseHist(hist);
        Visualise visualise = new Visualise(berekusoApiRetriever.getPrecipitationList(),berekusoApiRetriever.getTemperatureList(),berekusoApiRetriever.getUviList(), berekusoApiRetriever.getHeatindexList(),berekusoApiRetriever.getWindAnalysisList());
        visualise.visualiseTemperatureList(); // visualise berekuso temperature
        visualise.visualiseRainfallList(); // visualise berekuso rainfall
        visualise.visualiseUVIList(); // visualise berekuso uvi
        visualise.visualiseHeatIndexList(); // visualise berekuso heat index
        visualise.visualiseWindAnalysisList(); // visualise berekuso wind analysis
        ReportGenerator generate = new ReportGenerator(berekusoDataProcessor); 
        System.out.println(generate.generateReport()); // generate report for berekuso


    } 
}
