package com.jvmdevelop.mvp.reqandresp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public final class CreateChatRequest {
    private String title;
    private List<Long> userIds;
    private Boolean isTetATet;
    private Long recipientId;

}
