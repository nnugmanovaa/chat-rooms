package com.ps.chatrooms.model.exception;

import com.ps.chatrooms.model.errors.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemServiceException extends RuntimeException {
    private String message;
    private ErrorCode errorCode;
}
