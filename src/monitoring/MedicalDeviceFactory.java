package monitoring;

public class MedicalDeviceFactory {

    
    public static MedicalDevice createDevice(String deviceType) {
        if (deviceType == null) {
            throw new IllegalArgumentException("Device type cannot be null");
        }

        switch (deviceType.toUpperCase()) {
            case "ECG":
                return new ModernECGMachine();

            case "PULSE":
                return new PulseOximeter();

            case "LEGACY_HL7":
                
                LegacyHL7Device legacyDevice = new LegacyHL7Device();
                return new LegacyHL7DeviceAdapter(legacyDevice);

            default:
                throw new IllegalArgumentException("Unknown medical device type: " + deviceType);
        }
    }
}