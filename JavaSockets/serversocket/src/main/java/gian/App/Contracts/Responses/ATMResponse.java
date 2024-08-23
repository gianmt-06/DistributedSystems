package gian.App.Contracts.Responses;

import java.io.Serializable;

public record ATMResponse(
    String code,
    String title,
    String message
) implements Serializable {}
