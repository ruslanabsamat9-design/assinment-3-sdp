package monitoring;

public class PulseOximeter implements MedicalDevice {
    @Override
    public VitalsData readVitals() {
       
        return new VitalsData(72, 97);
    }

    @Override
    public void alertMedicalStaff(String message) {
        System.out.println("[Pulse Oximeter Alert]: " + message);
    }
}