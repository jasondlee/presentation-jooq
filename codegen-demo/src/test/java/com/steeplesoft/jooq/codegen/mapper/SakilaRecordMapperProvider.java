package com.steeplesoft.jooq.codegen.mapper;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;

public class SakilaRecordMapperProvider implements RecordMapperProvider {
    @Override
    public <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> clazz) {
        if (clazz.isAssignableFrom(ActorModel.class)) {
            return (RecordMapper<R, E>) new ActorMapper();
        }else if (clazz.isAssignableFrom(FilmModel.class)) {
            return (RecordMapper<R, E>) new FilmMapper();
        } else {
            return new DefaultRecordMapper(recordType, clazz);
        }
    }
}
