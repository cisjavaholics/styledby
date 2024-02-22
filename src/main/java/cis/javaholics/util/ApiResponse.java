package cis.javaholics.util;

public record ApiResponse<T>(boolean success, String message, Object data, Object error) {
}
