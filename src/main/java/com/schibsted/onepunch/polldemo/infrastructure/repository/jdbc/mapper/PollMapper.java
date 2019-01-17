package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    List<PollDto> selectPollDtoList(@Param("limit") Integer limit);

    PollDto selectOnePollDtoByPK(@Param("id") String id);
}
