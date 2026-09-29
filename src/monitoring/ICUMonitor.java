package monitoring;

public class ICUMonitor extends PatientMonitor {

    public ICUMonitor(MedicalDevice device) {
        super(device);
    }

    @Override
    public void checkPatientStatus() {
        try {
            VitalsData vitals = device.readVitals();
           
            if (vitals.getHeartRate() > 100 || vitals.getOxygenLevel() < 90) {
                device.alertMedicalStaff("CRITICAL: Patient status deteriorating in ICU!");
            } else {
                System.out.println("ICU Patient status normal: HR=" + vitals.getHeartRate() + ", SpO2=" + vitals.getOxygenLevel());
            }
        } catch (DeviceOperationException e) {
            device.alertMedicalStaff("CRITICAL DEVICE FAILURE: " + e.getMessage());
        }
    }
}