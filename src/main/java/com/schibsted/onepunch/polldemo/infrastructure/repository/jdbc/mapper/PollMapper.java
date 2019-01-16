package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    @ResultMap("PollResultMap")
    @Select("SELECT * FROM poll WHERE id = #{id}")
    Poll selectOneByPK(@Param("id") String id);
}
