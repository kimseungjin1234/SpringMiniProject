package org.zerock.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Data // Getter Setter 자동 생성 
public class BoardVO {
	
	//DB에 맞게 데이터 값 설정
	private Long bno;
	private String title, content, writer;
	private Date regdate, updateDate;

}
