package net.afrinic.event.scheduler.domain;

import lombok.Data;
import net.afrinic.event.scheduler.usecase.exception.ErrorResponse;

@Data
public class TestErrorResponseAccess {
    ErrorResponse errorResponse;
}
