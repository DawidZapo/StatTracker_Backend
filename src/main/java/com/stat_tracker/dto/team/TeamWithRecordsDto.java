package com.stat_tracker.dto.team;

import com.stat_tracker.dto.team.helper.Record;

import java.util.HashMap;
import java.util.Map;

public class TeamWithRecordsDto {
    Map<String, Record> records = new HashMap<>();

    public Map<String, Record> getRecords() {
        return records;
    }

    public void setRecords(Map<String, Record> records) {
        this.records = records;
    }
}
