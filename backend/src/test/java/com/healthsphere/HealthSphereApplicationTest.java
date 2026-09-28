package com.healthsphere;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthSphereApplicationTest {

    @Test
    void healthEndpointReturnsExpectedMessage() {
        HealthSphereApplication application = new HealthSphereApplication();

        String result = application.health();

        assertEquals("HealthSphere backend is running", result);
    }
}
