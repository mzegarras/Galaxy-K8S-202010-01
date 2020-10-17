package com.example.lab02.service;

import com.example.lab02.core.domain.Correlation;
import com.example.lab02.core.domain.EventStatus;
import com.example.lab02.core.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private StringRedisTemplate redisTemplate;

    public  ApplicationServiceImpl(StringRedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @RabbitListener(queues = "applications")
    @Override
    public void receiveApp(EventDto event) {
        log.debug(event.toString());

        updateCache(event.getCorrelation(),"status",event.getStatus().toString());

    }

    @Override
    public EventDto findByCorrelation(Correlation correlation) {
        Map<Object, Object> events =  getCache(correlation);

        EventDto eventDto = null;

        if(events!=null  && events.size()>0){
            eventDto = new EventDto();
            eventDto.setCorrelation(correlation);
            if (events.containsKey("status")){
                eventDto.setStatus(EventStatus.valueOf(events.get("status").toString()));
            }
        }



        return eventDto;
    }


    private void updateCache(Correlation correlation, String key, String value) {
        redisTemplate.opsForHash().put(getCacheKey(correlation), key, value == null ? "" : value);
        redisTemplate.expire(getCacheKey(correlation), 1, TimeUnit.DAYS);
    }

    private Map<Object, Object> getCache(Correlation correlation) {
        return redisTemplate.opsForHash().entries(getCacheKey(correlation));
    }


    private String getCacheKey(Correlation correlation) {
        return new StringJoiner(":")
                .add("applications")
                .add(correlation.getApplication().getApplicationId())
                .add(correlation.getCorrelationId())
                .toString();
    }

}
