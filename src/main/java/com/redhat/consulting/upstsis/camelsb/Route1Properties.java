package com.redhat.consulting.upstsis.camelsb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "route1") // Adjust prefix for each route
public class Route1Properties {
    private boolean autoStartup;

    // Getters and setters

    public boolean isAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }
}
