# Design Rationale Document

## 1. Chosen Problem Domain
The domain selected for this project is a **Medical Patient Monitoring System**. The system processes real-time vital signs (such as heart rate and oxygen saturation levels) from various medical hardware devices and triggers alerts based on specific medical contexts (e.g., Intensive Care Units vs. General Hospital Wards)[cite: 9].

---

## 2. Why Neither Bridge nor Adapter Alone Solves the Problem

### Why Bridge Alone is Not Enough
The **Bridge pattern** separates the abstraction hierarchy (`PatientMonitor`, `ICUMonitor`, `WardMonitor`) from the implementation hierarchy (`MedicalDevice`, `PulseOximeter`, `ModernECGMachine`)[cite: 9]. This prevents a class explosion when adding new monitoring logic or new modern medical devices[cite: 9]. However, Bridge assumes that all concrete implementations natively implement the target interface (`MedicalDevice`)[cite: 9]. It cannot directly accommodate third-party or legacy devices that have completely incompatible method signatures, parameters, and error handling mechanisms without modifying their original source code[cite: 9].

### Why Adapter Alone is Not Enough
The **Adapter pattern** solves the incompatibility issue by wrapping a legacy device (`LegacyHL7Device`) so it conforms to the `MedicalDevice` interface[cite: 9]. However, Adapter only addresses interface incompatibility between two specific types[cite: 9]. It does not solve the structural problem of combining multiple types of monitors with multiple types of devices[cite: 9]. Without Bridge, we would still suffer from class explosion (e.g., `ICULegacyAdapter`, `WardLegacyAdapter`, `ICUPulseOximeter`, etc.)[cite: 9].

### The Combined Solution
Combining both patterns allows the **Bridge** to handle structural variation across both axes (monitors and devices)[cite: 9], while the **Adapter** seamlessly plugs incompatible legacy systems into the Bridge's `MedicalDevice` implementor hierarchy[cite: 9].

---

## 3. Incompatibility of the Adapted Class (`LegacyHL7Device`)
The `LegacyHL7Device` class is genuinely incompatible with the `MedicalDevice` interface in three distinct ways[cite: 9]:

1. **Method Signature & Parameter Order:** Instead of returning a clean `VitalsData` object via `readVitals()`, it uses `fetchRawHL7Message(boolean includeHeader)` which returns raw string packets.
2. **Data Representation:** Data is returned as unparsed HL7 string formats requiring parsing logic to extract heart rate and $SpO_2$ values.
3. **Failure Mechanism:** Instead of throwing standard system exceptions (like `DeviceOperationException`), `LegacyHL7Device` throws native low-level exceptions (e.g., `HL7ConnectionException` or returns status code `-1`). The `LegacyHL7DeviceAdapter` catches these native exceptions and translates them into uniform `DeviceOperationException` types, preventing legacy specifics from leaking into the abstraction layer[cite: 9].

---

## 4. Limitation of the Final Design
* **Design Limitation:** Adding a completely new type of vital sign measurement (e.g., Body Temperature or Blood Pressure) requires modifying the core `MedicalDevice` interface and `VitalsData` model[cite: 9]. This violates the Open/Closed Principle on the data payload axis, as all existing device implementations and adapters would need to be updated to process the new measurement fields[cite: 9].

---

## How to Build and Run Tests

Run all unit tests using the standalone JUnit console runner[cite: 9]:

```powershell
javac -cp "lib/*" -d bin src/monitoring/*.java
java "-Dnet.bytebuddy.experimental=true" -jar lib/junit-platform-console-standalone-1.9.3.jar --class-path bin --select-package monitoring