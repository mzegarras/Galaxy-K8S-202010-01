package com.example.lab02.service;

import com.example.lab02.core.domain.Correlation;
import com.example.lab02.core.dto.EventDto;

public interface ApplicationService {

    void receiveApp(EventDto customerDto);
    EventDto findByCorrelation(Correlation correlation);

}
