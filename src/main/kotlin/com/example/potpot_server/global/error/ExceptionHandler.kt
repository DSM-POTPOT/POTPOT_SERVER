package com.example.potpot_server.global.error

import com.example.potpot_server.global.error.exception.POTPOTException
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler() {

    @ExceptionHandler(POTPOTException::class)
    fun handlingPickException(e: POTPOTException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }

//    @ExceptionHandler(MaxUploadSizeExceededException::class)
//    fun handleMaxSizeException(): ResponseEntity<ErrorResponse> {
//        val error = MaxUploadFileSizeException
//        return ResponseEntity(
//            ErrorResponse(error.errorCode.status, error.errorCode.message),
//            HttpStatus.BAD_REQUEST
//        )
//    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException, response: HttpServletResponse) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validatorExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            e.bindingResult.allErrors[0].defaultMessage?.let {
                ErrorResponse(
                    400,
                    it
                )
            },
            HttpStatus.BAD_REQUEST
        )
    }
}