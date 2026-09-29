package monitoring;
public class LegacyHL7DeviceAdapter implements MedicalDevice {

    private final LegacyHL7Device legacyDevice;

    public LegacyHL7DeviceAdapter(LegacyHL7Device legacyDevice) {
        this.legacyDevice = legacyDevice;
    }

    @Override
    public VitalsData readVitals() throws DeviceOperationException {
      
        LegacyHL7Device.HL7Payload payload = legacyDevice.fetchRawHL7Payload();

       
        if (payload == null || payload.status != 0) {
            int errorCode = (payload != null) ? payload.status : -999;
            throw new DeviceOperationException("Legacy HL7 Hardware Error. Code: " + errorCode);
        }

      
        try {
            String[] parts = payload.rawData.split(";");
            int hr = Integer.parseInt(parts[0].split(":")[1]);
            int spo2 = Integer.parseInt(parts[1].split(":")[1]);

            return new VitalsData(hr, spo2);
        } catch (Exception e) {
            throw new DeviceOperationException("Failed to parse raw HL7 telemetry data: " + payload.rawData);
        }
    }

    @Override
    public void alertMedicalStaff(String message) {
        
        byte[] signal = message.getBytes();
        int response = legacyDevice.sendRawSignal(signal);

        if (response != 200) {
            System.err.println("Legacy device failed to trigger alert, status code: " + response);
        }
    }
}