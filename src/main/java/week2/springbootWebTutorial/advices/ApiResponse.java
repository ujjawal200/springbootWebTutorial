package week2.springbootWebTutorial.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{

//    @Pattern(regexp = "hh-mm-ss dd-mm-yyyy")
    @JsonFormat(pattern =  "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    private T data;

    private ApiError error;


    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();
        this.data = data;
    }
    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }

}
