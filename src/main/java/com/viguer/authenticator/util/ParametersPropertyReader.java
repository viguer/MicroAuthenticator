package com.viguer.authenticator.util;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class ParametersPropertyReader {
    public Properties readProperties() {
        Resource resource = new ClassPathResource("parameters.properties");
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
