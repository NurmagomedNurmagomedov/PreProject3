package space.nurik.preproject3.task3.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {
    String message;
    long timeStamp;
}
