package monitoring;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LegacyHL7DeviceAdapterTest {

    @Mock
    
    private LegacyHL7Device mockLegacyDevice;

    @Test
    void testAdapterSuccessfulParsing() throws DeviceOperationException {
        
        when(mockLegacyDevice.fetchRawHL7Payload())
                .thenReturn(new LegacyHL7Device.HL7Payload(0, "HR:82;SPO2:96"));

        LegacyHL7DeviceAdapter adapter = new LegacyHL7DeviceAdapter(mockLegacyDevice);
        VitalsData vitals = adapter.readVitals();

        assertNotNull(vitals);
        assertEquals(82, vitals.getHeartRate());
        assertEquals(96, vitals.getOxygenLevel());
    }

    @Test
    void testAdapterFailureTranslation() {
      
        when(mockLegacyDevice.fetchRawHL7Payload())
                .thenReturn(new LegacyHL7Device.HL7Payload(-1, null));

        LegacyHL7DeviceAdapter adapter = new LegacyHL7DeviceAdapter(mockLegacyDevice);

      
        DeviceOperationException exception = assertThrows(
                DeviceOperationException.class,
                adapter::readVitals
        );

        assertTrue(exception.getMessage().contains("Code: -1"));
    }
}