package com.tapusd.graphqldemo.repository.sequence;

public interface CustomDatabaseSequenceRepository {
    long getNextSequence(String sequenceName);
}
