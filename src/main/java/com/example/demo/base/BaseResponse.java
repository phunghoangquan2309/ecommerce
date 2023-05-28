package com.example.demo.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Getter
@Setter
@JsonPropertyOrder({"status",  "message", "body"})
public class BaseResponse<T>  {
    private T body;

    private String message;

    private String status;

    String date_time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

    private BaseResponse(T body, String message, String status) {
        this.body = body;
        this.message = message;
        this.status = status;
    }

    public static <T> ResponseEntity<BaseResponse<T>> generateResponse(T body, String messsage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseResponse<T>(body, messsage, httpStatus.name()), httpStatus);
    }

    public static <T> ResponseEntity<BaseResponse<T>> generateResponseSuccess(T body) {
        return generateResponse(body, "success", HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> generateResponseError(String message) {
        return generateResponse(null, message == null ? "error": message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
