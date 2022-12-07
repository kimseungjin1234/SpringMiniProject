package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//현재 시간 불러오기
	@Select("select sysdate from dual")
	String getTime();
}
