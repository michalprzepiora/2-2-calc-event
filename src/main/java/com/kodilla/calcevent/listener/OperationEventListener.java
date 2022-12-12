package com.kodilla.calcevent.listener;

import com.kodilla.calcevent.event.CalcOperationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OperationEventListener implements ApplicationListener<CalcOperationEvent> {
    @Override
    public void onApplicationEvent(CalcOperationEvent event) {
        log.info("{} execute '{}' operation with result: {}", event.getSource().getClass(), event.getOperationName(), event.getResult());
    }
}
