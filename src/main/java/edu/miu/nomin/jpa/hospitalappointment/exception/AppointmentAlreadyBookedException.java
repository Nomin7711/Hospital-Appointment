package edu.miu.nomin.jpa.hospitalappointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppointmentAlreadyBookedException extends RuntimeException {
    public AppointmentAlreadyBookedException(String message) {
        super(message);
    }
}
