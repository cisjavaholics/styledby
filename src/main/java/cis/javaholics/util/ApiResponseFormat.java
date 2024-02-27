package cis.javaholics.util;

public record ApiResponseFormat<T>(boolean success, String message, Object data, Object error) {
}
