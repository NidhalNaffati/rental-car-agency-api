package com.CarRentalAgency.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private HttpStatus httpStatus;

    private String message;



    /*private LocalDateTime timestamp;

    private String field;

    private String details;*/


    // TODO: 04/10/2022 i should learn how to return  more details. 
    /*private String uri ;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date ;*/
}
