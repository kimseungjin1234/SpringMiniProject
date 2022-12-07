package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

  private int startPage;
  private int endPage;
  private boolean prev, next;

  private int total;
  private Criteria cri;

  public PageDTO(Criteria cri, int total) {

    this.cri = cri;
    this.total = total;

    									// 0.3 -> 1 -> 10
    this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10; //마지막 페이지 번호

    this.startPage = this.endPage - 9; // 1페이지 
    						
    								//71.0 / 10 -> 7.1 -> 8 
    int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount())); //마지막 페이지

    if (realEnd <= this.endPage) {
      this.endPage = realEnd;
    }

    this.prev = this.startPage > 1; // 이전 페이지

    this.next = this.endPage < realEnd; // 다음 페이지
  }
  
}

