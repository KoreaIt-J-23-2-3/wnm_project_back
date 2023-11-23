package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetIncomingAndOutgoingRespDto {
    private int historyId;
    private int count;
    private String createDate;
}
