package monitoring;

public class VitalsData {
    private final int heartRate;
    private final int oxygenLevel;

    public VitalsData(int heartRate, int oxygenLevel) {
        this.heartRate = heartRate;
        this.oxygenLevel = oxygenLevel;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }
}