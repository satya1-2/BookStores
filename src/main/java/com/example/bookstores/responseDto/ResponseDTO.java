package com.example.bookstores.responseDto;
import com.example.bookstores.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {

    private String message;
    private Object data;
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(Object data) {
        this.data= data;
    }

}
