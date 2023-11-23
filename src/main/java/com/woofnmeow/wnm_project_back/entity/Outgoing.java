package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetIncomingAndOutgoingRespDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Outgoing {
    private int outgoingHistoryId;
    private int productDtlId;
    private int outgoingCount;
    private LocalDateTime outgoingDate;

    public GetIncomingAndOutgoingRespDto toRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetIncomingAndOutgoingRespDto.builder()
                .historyId(outgoingHistoryId)
                .count(outgoingCount)
                .createDate(outgoingDate.format(formatter))
                .build();
    }
}
