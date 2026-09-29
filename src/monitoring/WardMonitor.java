package monitoring;

public class WardMonitor extends PatientMonitor {

    public WardMonitor(MedicalDevice device) {
        super(device);
    }

    @Override
    public void checkPatientStatus() {
        try {
            VitalsData vitals = device.readVitals();
            
            if (vitals.getOxygenLevel() < 92) {
                device.alertMedicalStaff("WARNING: Low oxygen level in general ward.");
            } else {
                System.out.println("Ward Patient status normal: HR=" + vitals.getHeartRate() + ", SpO2=" + vitals.getOxygenLevel());
            }
        } catch (DeviceOperationException e) {
            System.err.println("Ward logging: Device error - " + e.getMessage());
        }
    }
}