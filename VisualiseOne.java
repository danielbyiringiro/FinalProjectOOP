import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VisualiseOne {

    private List<Double> data;

    public VisualiseOne(List<Double> data) {
        this.data = data;
    }

    public void showChart() {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();

                // Draw x and y axis
                g.setColor(Color.BLACK);
                g.drawLine(50, height - 50, width - 50, height - 50); // x axis
                g.drawLine(50, height - 50, 50, 50); // y axis

                // Draw labels for x and y axis
                g.drawString("Time", width / 2, height - 20); // x label
                g.drawString("Value", 20, height / 2); // y label

                // Draw data points
                g.setColor(Color.RED);
                int numPoints = data.size();
                double maxValue = data.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                double minValue = data.stream().mapToDouble(Double::doubleValue).min().orElse(0);
                for (int i = 0; i < numPoints; i++) {
                    double x = 50 + (i * (width - 100) / numPoints);
                    double y = height - 50 - ((data.get(i) - minValue) / (maxValue - minValue)) * (height - 100);
                    g.fillOval((int)x - 3, (int)y - 3, 6, 6);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        WeatherAPIRetriever berekusoApiRetriever = new WeatherAPIRetriever("Berekuso");
        String hist = berekusoApiRetriever.getHistoricalWeather(berekusoApiRetriever);
        berekusoApiRetriever.parseHist(hist);
        List<Double> temperatureList = berekusoApiRetriever.getTemperatureList();
        berekusoApiRetriever.printTemperatureList();
        VisualiseOne visualise = new VisualiseOne(temperatureList);
        visualise.showChart();



    }

}
