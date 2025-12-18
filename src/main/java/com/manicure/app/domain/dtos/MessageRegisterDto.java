package com.manicure.app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRegisterDto {
    private String whatsappId;
    private String content;
    private boolean isMessageSentByClient;
}
