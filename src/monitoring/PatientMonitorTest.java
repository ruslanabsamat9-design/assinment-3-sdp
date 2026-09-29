package monitoring;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientMonitorTest {

    @Mock
    private MedicalDevice mockDevice;

    @Test
    void testICUMonitorTriggersAlertOnCriticalVitals() throws DeviceOperationException {
       
        when(mockDevice.readVitals()).thenReturn(new VitalsData(110, 88));

        PatientMonitor icuMonitor = new ICUMonitor(mockDevice);
        icuMonitor.checkPatientStatus();

        verify(mockDevice, times(1)).readVitals();
        verify(mockDevice, times(1)).alertMedicalStaff(contains("CRITICAL"));
    }

    @Test
    void testWardMonitorNormalStatusNoAlert() throws DeviceOperationException {
        
        when(mockDevice.readVitals()).thenReturn(new VitalsData(70, 98));

        PatientMonitor wardMonitor = new WardMonitor(mockDevice);
        wardMonitor.checkPatientStatus();

        verify(mockDevice, times(1)).readVitals();
        verify(mockDevice, never()).alertMedicalStaff(anyString());
    }
}