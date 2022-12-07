package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getList(); // 테이블 리스트
	
	void insert(BoardVO board); //게시물 등록 
	
	void insertSelectKey(BoardVO board); // 게시물 등록
	//insert문이 실행되고, 생성된 PK 값을 알아야 하는 경우 <selectkey> 사용 

	BoardVO read(Long bno);  // Key 값으로 가져와야해서 bno 
	
	int delete(Long bno);  //데이터 삭제
	
	int update(BoardVO board); //데이터 업데이트
	
	List<BoardVO> getListWithPaging(Criteria cri); //페이지 처리
	
	int getTotalCount(Criteria cri); // 총 페이지 수 
	
	List<BoardVO> searchTest(Map<String,Map<String,String>>map);
	
}
