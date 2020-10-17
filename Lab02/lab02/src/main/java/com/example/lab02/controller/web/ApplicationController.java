package com.example.lab02.controller.web;


import com.example.lab02.controller.web.dto.CorrelationWebDto;
import com.example.lab02.core.domain.Application;
import com.example.lab02.core.domain.Correlation;
import com.example.lab02.core.dto.EventDto;
import com.example.lab02.service.ApplicationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class ApplicationController {

    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService=applicationService;
    }

    @GetMapping(value = "/correlations/{correlation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CorrelationWebDto> getCorrelation(@PathVariable("correlation_id") String correlationId) {
        Correlation correlation = new Correlation();
        Application application = new Application();
        application.setApplicationId("app01");
        correlation.setApplication(application);
        correlation.setCorrelationId(correlationId);

        EventDto eventDto = applicationService.findByCorrelation(correlation);

        CorrelationWebDto correlationWebDto = new CorrelationWebDto();

        if(eventDto!=null){
            correlationWebDto.setEventDto(eventDto);
            return new ResponseEntity<>(correlationWebDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}
