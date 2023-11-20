package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetIncomingAndOutgoingRespDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Incoming {
    private int incomingHistoryId;
    private int productDtlId;
    private int incomingCount;
    private LocalDateTime incomingDate;

    public GetIncomingAndOutgoingRespDto toRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetIncomingAndOutgoingRespDto.builder()
                .historyId(incomingHistoryId)
                .count(incomingCount)
                .createDate(incomingDate.format(formatter))
                .build();
    }
}
