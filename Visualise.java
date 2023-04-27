import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Visualise {
    private List<Double> precipitationList;
    private List<Double> temperatureList;
    private List<Double> uviList;
    private List<Double> heatindexList;
    private List<Double> windAnalysisList;



    public Visualise(List<Double> precipitationList, List<Double> temperatureList, List<Double> uviList,
            List<Double> heatindexList, List<Double> windAnalysisList) {
        this.precipitationList = precipitationList;
        this.temperatureList = temperatureList;
        this.uviList = uviList;
        this.heatindexList = heatindexList;
        this.windAnalysisList = windAnalysisList;
    }

    public void visualiseTemperatureList() {
        JFrame frame = new JFrame("Temperature Over The Day");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new TemperatureListPanel(temperatureList));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void visualiseRainfallList() {
        JFrame frame = new JFrame("Rainfall Over The Day");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new RainfallListPanel(precipitationList));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void visualiseUVIList() {
        JFrame frame = new JFrame("UVI Over The Day");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new UVIListPanel(uviList));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void visualiseHeatIndexList() {
        JFrame frame = new JFrame("Heat Index Over The Day");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new HeatIndexListPanel(heatindexList));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void visualiseWindAnalysisList() {
        JFrame frame = new JFrame("Wind Analysis Over The Day");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new WindAnalysisListPanel(windAnalysisList));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static class TemperatureListPanel extends JPanel {
        private static final int BAR_WIDTH = 50;
        private static final int PADDING = 40;
        
        private List<Double> temperatureList;
        public TemperatureListPanel(List<Double> temperatureList) {
            this.temperatureList = temperatureList;
            setPreferredSize(new Dimension(temperatureList.size() * (BAR_WIDTH + PADDING) + PADDING, 300));
        }
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
            
                double maxTemperature = temperatureList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                int x = PADDING;
                int yLabelOffset = 20; // distance between the y-axis and y-axis label
                int xLabelOffset = 20; // distance between the x-axis and x-axis label
            
                // Draw x-axis label
                g.drawString("Hours of the Day", getWidth() / 2 - g.getFontMetrics().stringWidth("Hours of the Day") / 2,
                        getHeight() - PADDING / 2 + xLabelOffset);
            
                for (int i = 0; i < 24; i++) {
                    // Draw x-axis tick
                    int xTick = x + (BAR_WIDTH + PADDING) / 2;
                    int yTick = getHeight() - PADDING / 2;
                    g.drawLine(xTick, yTick, xTick, yTick - PADDING / 2);
            
                    // Draw x-axis label
                    String hourLabel = String.format("%02d:00", i);
                    g.drawString(hourLabel, xTick - g.getFontMetrics().stringWidth(hourLabel) / 2,
                            getHeight() - PADDING / 2 + xLabelOffset);
            
                    x += BAR_WIDTH + PADDING;
                }
            
                // Draw y-axis label
                String yAxisLabel = "Temperature (°C)";
                ((Graphics2D) g).rotate(-Math.PI / 2);
                g.drawString(yAxisLabel, -getHeight() / 2 - g.getFontMetrics().stringWidth(yAxisLabel) / 2, PADDING - yLabelOffset);
                ((Graphics2D) g).rotate(Math.PI / 2);
            
                // Draw bars
                x = PADDING;
                for (Double temperature : temperatureList) {
                    int height = (int) (temperature * 10);
                    int y = getHeight() - PADDING - height;
                    g.setColor(Color.RED);
                    g.fillRect(x, y, BAR_WIDTH, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BAR_WIDTH, height);
                    x += BAR_WIDTH + PADDING;
                }
            }
        }

    private static class RainfallListPanel extends JPanel {
        private static final int BAR_WIDTH = 50;
        private static final int PADDING = 40;
        
        private List<Double> precipitationList;
        public RainfallListPanel(List<Double> precipitationList) {
            this.precipitationList = precipitationList;
            setPreferredSize(new Dimension(precipitationList.size() * (BAR_WIDTH + PADDING) + PADDING, 300));
        }
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
            
                //double maxPrecipitation = precipitationList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                int x = PADDING;
                int yLabelOffset = 20; // distance between the y-axis and y-axis label
                int xLabelOffset = 20; // distance between the x-axis and x-axis label
            
                // Draw x-axis label
                g.drawString("Hours of the Day", getWidth() / 2 - g.getFontMetrics().stringWidth("Hours of the Day") / 2,
                        getHeight() - PADDING / 2 + xLabelOffset);
            
                for (int i = 0; i < 24; i++) {
                    // Draw x-axis tick
                    int xTick = x + (BAR_WIDTH + PADDING) / 2;
                    int yTick = getHeight() - PADDING / 2;
                    g.drawLine(xTick, yTick, xTick, yTick - PADDING / 2);
            
                    // Draw x-axis label
                    String hourLabel = String.format("%02d:00", i);
                    g.drawString(hourLabel, xTick - g.getFontMetrics().stringWidth(hourLabel) / 2,
                            getHeight() - PADDING / 2 + xLabelOffset);
            
                    x += BAR_WIDTH + PADDING;
                }
            
                // Draw y-axis label
                String yAxisLabel = "Precipitation (mm)";
                ((Graphics2D) g).rotate(-Math.PI / 2);
                g.drawString(yAxisLabel, -getHeight() / 2 - g.getFontMetrics().stringWidth(yAxisLabel) / 2, PADDING - yLabelOffset);
                ((Graphics2D) g).rotate(Math.PI / 2);
            
                // Draw bars
                x = PADDING;
                for (Double precipitation : precipitationList)
                {
                    int height = (int) (precipitation * 10);
                    int y = getHeight() - PADDING - height;
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, BAR_WIDTH, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BAR_WIDTH, height);
                    x += BAR_WIDTH + PADDING;
                }

            }
    }
    
    private static class UVIListPanel extends JPanel{
        private static final int BAR_WIDTH = 50;
        private static final int PADDING = 40;
        
        private List<Double> uviList;
        public UVIListPanel(List<Double> uviList) {
            this.uviList = uviList;
            setPreferredSize(new Dimension(uviList.size() * (BAR_WIDTH + PADDING) + PADDING, 300));
        }
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
            
                //double maxUvi = uviList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                int x = PADDING;
                int yLabelOffset = 20; // distance between the y-axis and y-axis label
                int xLabelOffset = 20; // distance between the x-axis and x-axis label
            
                // Draw x-axis label
                g.drawString("Hours of the Day", getWidth() / 2 - g.getFontMetrics().stringWidth("Hours of the Day") / 2,
                        getHeight() - PADDING / 2 + xLabelOffset);
            
                for (int i = 0; i < 24; i++) {
                    // Draw x-axis tick
                    int xTick = x + (BAR_WIDTH + PADDING) / 2;
                    int yTick = getHeight() - PADDING / 2;
                    g.drawLine(xTick, yTick, xTick, yTick - PADDING / 2);
            
                    // Draw x-axis label
                    String hourLabel = String.format("%02d:00", i);
                    g.drawString(hourLabel, xTick - g.getFontMetrics().stringWidth(hourLabel) / 2,
                            getHeight() - PADDING / 2 + xLabelOffset);
            
                    x += BAR_WIDTH + PADDING;
                }
            
                // Draw y-axis label
                String yAxisLabel = "UV Index (UVI)";
                ((Graphics2D) g).rotate(-Math.PI / 2);
                ((Graphics2D) g).rotate(-Math.PI / 2);
                g.drawString(yAxisLabel, -getHeight() / 2 - g.getFontMetrics().stringWidth(yAxisLabel) / 2, PADDING - yLabelOffset);
                ((Graphics2D) g).rotate(Math.PI / 2);
            
                // Draw bars
                x = PADDING;
                for (Double uvi : uviList) {
                    int height = (int) (uvi * 10);
                    int y = getHeight() - PADDING - height;
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, BAR_WIDTH, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BAR_WIDTH, height);
                    x += BAR_WIDTH + PADDING;
                }

    }
    }

    private static class HeatIndexListPanel extends JPanel{
        private static final int BAR_WIDTH = 50;
        private static final int PADDING = 40;
        
        private List<Double> heatindexList;
        public HeatIndexListPanel(List<Double> heatindexList) {
            this.heatindexList = heatindexList;
            setPreferredSize(new Dimension(heatindexList.size() * (BAR_WIDTH + PADDING) + PADDING, 300));
        }
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
            
                //double maxHeatindex = heatindexList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                int x = PADDING;
                int yLabelOffset = 20; // distance between the y-axis and y-axis label
                int xLabelOffset = 20; // distance between the x-axis and x-axis label
            
                // Draw x-axis label
                g.drawString("Hours of the Day", getWidth() / 2 - g.getFontMetrics().stringWidth("Hours of the Day") / 2,
                        getHeight() - PADDING / 2 + xLabelOffset);
            
                for (int i = 0; i < 24; i++) {
                    // Draw x-axis tick
                    int xTick = x + (BAR_WIDTH + PADDING) / 2;
                    int yTick = getHeight() - PADDING / 2;
                    g.drawLine(xTick, yTick, xTick, yTick - PADDING / 2);
            
                    // Draw x-axis label
                    String hourLabel = String.format("%02d:00", i);
                    g.drawString(hourLabel, xTick - g.getFontMetrics().stringWidth(hourLabel) / 2,
                            getHeight() - PADDING / 2 + xLabelOffset);
            
                    x += BAR_WIDTH + PADDING;
                }
            
                // Draw y-axis label
                String yAxisLabel = "Heat Index (C)";
                ((Graphics2D) g).rotate(-Math.PI / 2);
                g.drawString(yAxisLabel, -getHeight() / 2 - g.getFontMetrics().stringWidth(yAxisLabel) / 2, PADDING - yLabelOffset);
                ((Graphics2D) g).rotate(Math.PI / 2);
            
                // Draw bars
                x = PADDING;
                for (Double heat : heatindexList) {
                    int height = (int) (heat * 10);
                    int y = getHeight() - PADDING - height;
                    g.setColor(Color.RED);
                    g.fillRect(x, y, BAR_WIDTH, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BAR_WIDTH, height);
                    x += BAR_WIDTH + PADDING;
                }
            }
    }

    private static class WindAnalysisListPanel extends JPanel{
        private static final int BAR_WIDTH = 50;
        private static final int PADDING = 40;
        
        private List<Double> windAnalysisList;
        public WindAnalysisListPanel(List<Double> windAnalysisList) {
            this.windAnalysisList = windAnalysisList;
            setPreferredSize(new Dimension(windAnalysisList.size() * (BAR_WIDTH + PADDING) + PADDING, 300));
        }
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
            
                //double maxWindAnalysis = windAnalysisList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                int x = PADDING;
                int yLabelOffset = 20; // distance between the y-axis and y-axis label
                int xLabelOffset = 20; // distance between the x-axis and x-axis label
            
                // Draw x-axis label
                g.drawString("Hours of the Day", getWidth() / 2 - g.getFontMetrics().stringWidth("Hours of the Day") / 2,
                        getHeight() - PADDING / 2 + xLabelOffset);
            
                for (int i = 0; i < 24; i++) {
                    // Draw x-axis tick
                    int xTick = x + (BAR_WIDTH + PADDING) / 2;
                    int yTick = getHeight() - PADDING / 2;
                    g.drawLine(xTick, yTick, xTick, yTick - PADDING / 2);
            
                    // Draw x-axis label
                    String hourLabel = String.format("%02d:00", i);
                    g.drawString(hourLabel, xTick - g.getFontMetrics().stringWidth(hourLabel) / 2,
                            getHeight() - PADDING / 2 + xLabelOffset);
            
                    x += BAR_WIDTH + PADDING;
                }
            
                // Draw y-axis label
                String yAxisLabel = "Wind Analysis (MPH)";
                ((Graphics2D) g).rotate(-Math.PI / 2);
                g.drawString(yAxisLabel, -getHeight() / 2 - g.getFontMetrics().stringWidth(yAxisLabel) / 2, PADDING - yLabelOffset);
                ((Graphics2D) g).rotate(Math.PI / 2);
            
                // Draw bars
                x = PADDING;
                for (Double wind : windAnalysisList) {
                    int height = (int) (wind * 10);
                    int y = getHeight() - PADDING - height;
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, BAR_WIDTH, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BAR_WIDTH, height);
                    x += BAR_WIDTH + PADDING;
                }
            }
    }

    }


