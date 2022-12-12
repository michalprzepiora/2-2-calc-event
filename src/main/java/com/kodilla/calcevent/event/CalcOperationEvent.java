package com.kodilla.calcevent.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CalcOperationEvent  extends ApplicationEvent {
    private String operationName;
    private String result;

    public CalcOperationEvent(Object source, String operationName, String result) {
        super(source);
        this.operationName = operationName;
        this.result = result;
    }
}
