package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    @ResultMap("PollResultMap")
    @Select("SELECT * FROM poll WHERE id = #{id}")
    Poll selectOneByPK(@Param("id") String id);

    List<PollDto> selectPollDtoList(@Param("limit") Integer limit);

    PollDto selectOnePollDtoByPK(@Param("id") String id);
}
