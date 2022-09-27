package edu.miu.cs590.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto implements Serializable {

    private String from;
    private String to;
    private String subject;
    private String message;
}
