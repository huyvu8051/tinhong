package com.bobvu.tinhong.cassandra.message;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListMessageResponse {
    private List<MessageResponse> messages;
}
