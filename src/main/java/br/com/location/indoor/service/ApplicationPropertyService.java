package br.com.location.indoor.service;

import org.springframework.stereotype.Service;

@Service
public class ApplicationPropertyService {

    private static final String _5000 = "5000";

    public String getApplicationProperty() {
        return _5000;
    }

}
