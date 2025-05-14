package com.records.common.exceptions;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
