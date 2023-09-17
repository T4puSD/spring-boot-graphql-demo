package com.tapusd.graphqldemo.repository.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequence")
public class DatabaseSequence {
    @Id
    private String id;
    private long sequence;

    public String getId() {
        return id;
    }

    public DatabaseSequence setId(String sequenceName) {
        this.id = sequenceName;
        return this;
    }

    public long getSequence() {
        return sequence;
    }

    public DatabaseSequence setSequence(long sequence) {
        this.sequence = sequence;
        return this;
    }

    @Override
    public String toString() {
        return "DatabaseSequence{" +
                "id='" + id + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}
