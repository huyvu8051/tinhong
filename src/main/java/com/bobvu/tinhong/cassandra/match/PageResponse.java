package com.bobvu.tinhong.cassandra.match;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> list;
    private long totalElement;

    public PageResponse() {
        list = new ArrayList<>();
        totalElement = 0;
    }
}
