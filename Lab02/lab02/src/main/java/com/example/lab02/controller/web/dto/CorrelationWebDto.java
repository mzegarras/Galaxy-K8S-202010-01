package com.example.lab02.controller.web.dto;

import com.example.lab02.core.dto.EventDto;

public class CorrelationWebDto {

    private EventDto eventDto;

    public EventDto getEventDto() {
        return eventDto;
    }

    public void setEventDto(EventDto eventDto) {
        this.eventDto = eventDto;
    }
}
