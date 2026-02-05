package edu.miu.nomin.jpa.hospitalappointment.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;
    private final String queueName = "smsQueue";

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    public void sendAppointmentCreatedSms(String name, String phoneNumber, String appointmentId, String date) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Name", name);
        params.put("PhoneNumber", phoneNumber);
        params.put("MessageType", "APPOINTMENT_CREATED");
        params.put("AppointmentId", appointmentId);
        params.put("Date", date);

        jmsTemplate.convertAndSend(queueName, params);
        System.out.println("Appointment Created SMS sent to queue: " + params);
    }

    public void sendSmsMessage(String Name, String phoneNumber, String status, String appointmentId, String date) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Name", Name);
        params.put("PhoneNumber", phoneNumber);
        params.put("MessageType", "STATUS_CHANGED");
        params.put("Status", status);
        params.put("AppointmentId", appointmentId);
        params.put("Date", date);

        jmsTemplate.convertAndSend(queueName, params);
        System.out.println("SMS message sent to queue: " + params);
    }
}
