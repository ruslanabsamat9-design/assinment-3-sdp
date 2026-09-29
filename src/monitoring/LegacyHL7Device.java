package monitoring;

public class LegacyHL7Device {

    
    public static class HL7Payload {
        public int status;      // 0 = OK, -1 = SENSOR_ERROR, -2 = DISCONNECTED
        public String rawData; 

        public HL7Payload(int status, String rawData) {
            this.status = status;
            this.rawData = rawData;
        }
    }

    
    public HL7Payload fetchRawHL7Payload() {
        
        return new HL7Payload(0, "HR:80;SPO2:98");
    }

  
    public int sendRawSignal(byte[] bytes) {
        return 200; 
    }
}