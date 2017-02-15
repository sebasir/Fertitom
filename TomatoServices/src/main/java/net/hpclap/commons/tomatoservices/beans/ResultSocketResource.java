package net.hpclap.commons.tomatoservices.beans;

import java.io.Serializable;
import org.primefaces.component.message.Message;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/results")
public class ResultSocketResource implements Serializable {

    public static final long serialVersionUID = 1L;

    @OnMessage(encoders = {JSONEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }
}
