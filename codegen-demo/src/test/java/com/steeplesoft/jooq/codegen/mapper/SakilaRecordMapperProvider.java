package com.steeplesoft.jooq.codegen.mapper;

import java.util.HashMap;
import java.util.Map;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;

public class SakilaRecordMapperProvider implements RecordMapperProvider {
    private Map<Class, RecordMapper> mappers = new HashMap<>();
//    /*
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
//    */
/*

    @Override
    public <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> clazz) {
        RecordMapper<R,E> mapper = getMapper(clazz);
        return (mapper != null) ? mapper : new DefaultRecordMapper(recordType, clazz);
    }

    private RecordMapper getMapper(Class clazz) {
        RecordMapper mapper = null; //mappers.get(clazz);

        if (mapper == null) {
            if (clazz.isAssignableFrom(ActorModel.class)) {
                mapper =  new ActorMapper();
            } else if (clazz.isAssignableFrom(FilmModel.class)) {
                mapper = new FilmMapper();
            }

            if (mapper != null) {
//                mappers.put(clazz, mapper);
            }
        }

        return mapper;
    }
*/
}
