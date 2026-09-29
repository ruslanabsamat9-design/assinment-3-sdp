package monitoring;

public class ModernECGMachine implements MedicalDevice {
    @Override
    public VitalsData readVitals() {
        
        return new VitalsData(75, 98);
    }

    @Override
    public void alertMedicalStaff(String message) {
        System.out.println("[Modern ECG Machine Alert]: " + message);
    }
}