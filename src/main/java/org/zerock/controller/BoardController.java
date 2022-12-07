package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	private final BoardService service;

	@GetMapping("list") // Model : 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할
	public void list(Criteria cri, Model model) {
		log.info(".........................................");
		log.info(cri);
		log.info("list..................");
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker",new PageDTO(cri,service.getTotal(cri))); 
	}

	@GetMapping("/register") // 게시물 등록 화면 제공
	public void registerGET() {

	}

	@PostMapping("/register") // 끝나고 목록 페이지로 넘어가기 위한 코드
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		//RedirectAttributes : 목록으로 다시 돌아가라 
		log.info("board : " + board);
		Long bno = service.register(board);
		log.info("BNO : " + bno);

		rttr.addFlashAttribute("result", bno); // 잠깐만 결과 값을 보여달라
		
		return "redirect:/board/list";
	}

	@GetMapping({ "/get", "/modify" })// 조회 기능 
	public void get(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify") // 수정하고 목록 페이지로 넘어가는 코드
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		
		//처리가 끝난 걸 저장
		int count = service.modify(board);
		
		//끝났을 때 성공했다는 문자 보내기
		if (count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getAmount());
		rttr.addAttribute("keyword", cri.getAmount());
		
		//수정 완료 하면 리스트 페이지로 가라
		return "redirect:/board/list";
	}

	@PostMapping("/remove") // 삭제하고 목록 페이지로 넘어가는 코드 
	public String remove(@RequestParam Long bno, Criteria cri, RedirectAttributes rttr) {
		
		//처리가 끝난 걸 저장
		int count = service.remove(bno);
		
		//끝났을 때 성공했다는 문자 보내기
		if (count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getAmount());
		rttr.addAttribute("keyword", cri.getAmount());
		
		//삭제 완료 하면 리스트 페이지로 가라
		return "redirect:/board/list";
	}

}
