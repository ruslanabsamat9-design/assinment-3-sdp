package monitoring;

public interface MedicalDevice {
    VitalsData readVitals() throws DeviceOperationException;
    void alertMedicalStaff(String message);
}