package com.tapusd.graphqldemo.repository.sequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomDatabaseSequenceRepositoryImpl implements CustomDatabaseSequenceRepository {

    private final MongoTemplate mongoTemplate;

    public CustomDatabaseSequenceRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long getNextSequence(String sequenceName) {
        DatabaseSequence seq = mongoTemplate.findAndModify(
                query(where("id").is(sequenceName)),
                new Update().inc("sequence", 1),
                options().returnNew(true).upsert(true), DatabaseSequence.class);

        return Optional.ofNullable(seq)
                .map(DatabaseSequence::getSequence)
                .orElse(1L);
    }
}
