package monitoring;

public abstract class PatientMonitor {
    
    protected final MedicalDevice device;

    public PatientMonitor(MedicalDevice device) {
        this.device = device;
    }

    
    public abstract void checkPatientStatus();
}