package org.md2k.mcerebrum;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.Context;
import org.md2k.mcerebrum.phonesensor.phone.sensors.Accelerometer;



@RunWith(MockitoJUnitRunner.class)
public class AccelerometerTest {

    @Mock
    Context mMockContext;

    @Test
    public void testAccelNotNull() {
        assertNotNull("should not be null", new Accelerometer(mMockContext));
    }
}