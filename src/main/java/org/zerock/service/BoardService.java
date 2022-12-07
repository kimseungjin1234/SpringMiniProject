package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

	Long register(BoardVO board); //등록 처리

	BoardVO get(Long bno); //read

	int modify(BoardVO board); //수정

	int remove(Long bno); //삭제

	List<BoardVO> getList(); // 전체 목록 가져오기

	List<BoardVO> getList(Criteria cri); 
	
	int getTotal(Criteria cri);
}
