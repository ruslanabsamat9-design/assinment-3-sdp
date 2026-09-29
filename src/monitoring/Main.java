package monitoring;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== MEDICAL PATIENT MONITORING SYSTEM ===\n");

       
        MedicalDevice ecgDevice = MedicalDeviceFactory.createDevice("ECG");
        MedicalDevice pulseDevice = MedicalDeviceFactory.createDevice("PULSE");
        MedicalDevice legacyDevice = MedicalDeviceFactory.createDevice("LEGACY_HL7");

        
        PatientMonitor icuMonitorWithECG = new ICUMonitor(ecgDevice);
        PatientMonitor wardMonitorWithPulse = new WardMonitor(pulseDevice);
        PatientMonitor icuMonitorWithLegacy = new ICUMonitor(legacyDevice);

        
        System.out.println("--- 1. Testing ICU Monitor with Modern ECG ---");
        icuMonitorWithECG.checkPatientStatus();

        System.out.println("\n--- 2. Testing Ward Monitor with Pulse Oximeter ---");
        wardMonitorWithPulse.checkPatientStatus();

        System.out.println("\n--- 3. Testing ICU Monitor with Legacy HL7 Device (via Adapter) ---");
        icuMonitorWithLegacy.checkPatientStatus();
    }
}