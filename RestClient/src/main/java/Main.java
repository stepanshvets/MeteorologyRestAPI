public class Main {
    public static void main(String[] args) {
        Client client = new Client();

//        client.registerSensor("Test sensor name");
//        client.sendMeasurements("Test sensor name");
        client.rainyDaysCount();
        client.plot();
    }
}
