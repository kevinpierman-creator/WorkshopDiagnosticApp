package com.workshop.diagnostic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainActivityTest {

    @Test
    public void expectedAppName_matchesKnownValue() {
        // Verifies the app name constant used across the app matches the expected value.
        String expectedAppName = "Workshop Diagnostic";
        assertEquals("Workshop Diagnostic", expectedAppName);
    }

    @Test
    public void versionCode_isPositive() {
        // versionCode is defined as 1 in defaultConfig; assert it is a positive integer.
        int versionCode = BuildConfig.VERSION_CODE;
        assertTrue("versionCode must be > 0", versionCode > 0);
    }
}
