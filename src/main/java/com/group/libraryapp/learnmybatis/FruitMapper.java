package com.group.libraryapp.learnmybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FruitMapper {
    @Select("SELECT * FROM TB_FRUIT")
    List<Fruit> findAll();

    @Select("SELECT * FROM TB_FRUIT WHERE name = #{name}")
    List<Fruit> findByName(@Param("name") String name);
}
