import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Client {
    private RestTemplate restTemplate = new RestTemplate();

//    public static void main(String[] args) {
//
//
//
//
//
////
//
//
//
//    }

    public void registerSensor(String sensorName) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorName);

        HttpEntity<Sensor> request = new HttpEntity<>(sensor);
        String urlPostSensor = "http://localhost:8080/sensors/registration";


        try {
            String response = restTemplate.postForObject(urlPostSensor, request,  String.class);
            System.out.println(response);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMeasurements(String sensorName) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorName);

        Random random = new Random();
        String urlPostMeasurement = "http://localhost:8080/measurements/add";
        for (int i = 0; i < 10; i++) {
            Measurement measurement = new Measurement(-100 + random.nextInt(200),
                    random.nextBoolean(), sensor);
            HttpEntity<Measurement> request = new HttpEntity<>(measurement);
            try {
                String response = restTemplate.postForObject(urlPostMeasurement, request, String.class);
                System.out.println(response);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void rainyDaysCount() {
        String urlGetRainyDaysCount = "http://localhost:8080/measurements/rainyDaysCount";
        Integer rainyDaysCount = restTemplate.getForObject(urlGetRainyDaysCount, Integer.class);
        System.out.println(rainyDaysCount);
    }

    public List<Measurement> getMeasurements() {
        String urlGetMeasurements = "http://localhost:8080/measurements";
        Measurement[] measurements = restTemplate.getForObject(urlGetMeasurements, Measurement[].class);
        List<Measurement> measurementList = Arrays.asList(measurements);
        return measurementList;
    }

    public void plot(){
        List<Measurement> measurementList = getMeasurements();
        int length = measurementList.size();
        double[] xData = new double[length];
        double[] yData = new double[length];
        int i = 0;
        for (Measurement measurement: measurementList){
            yData[i] = measurement.getValue();
            xData[i] = i + 1;
            i += 1;
        }

        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }
}
