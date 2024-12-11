package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class TransactionHistoryDTO implements Serializable {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("records")
    private List<TransactionDTO> transactionDTOList;

    public TransactionHistoryDTO(Integer offset, Integer limit, List<TransactionDTO> transactionDTOList) {
        this.offset = Objects.nonNull(offset) ? offset : 0;
        this.limit = Objects.nonNull(limit) ? limit : 0;
        this.transactionDTOList = transactionDTOList;
    }
}
