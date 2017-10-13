package com.example.utility;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * Created by diko.raditya on 31/08/2017.
 */
public class BeanMapper {

    public static <S, C> C map(S source, Class<C> clazz) {
            return mapper.map(source, clazz);
        }

    public static <S, C> List<C> mapAsList(Iterable<S> source, Class<C> clazz) {
        return mapper.mapAsList(source, clazz);
    }

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private static MapperFacade mapper = mapperFactory.getMapperFacade();
}
