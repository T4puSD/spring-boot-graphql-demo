package com.tapusd.graphqldemo.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequenceRepository extends
        MongoRepository<DatabaseSequence, String>,
        CustomDatabaseSequenceRepository {
}
