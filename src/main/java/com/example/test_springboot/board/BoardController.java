package com.example.test_springboot.board;

import com.example.test_springbootbasic.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardService.글목록보기();
        request.setAttribute("models", boardList);
        return "board/list";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다. 로그인하세요.");
        return "board/save-form";
    }

    @GetMapping("/board/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다. 로그인하세요.");
        BoardResponse.UpdateDTO updateDTO = boardService.글수정화면(id);
        request.setAttribute("model", updateDTO);
        return "board/update-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        BoardResponse.DetailDTO detailDTO = boardService.글상세보기(id);
        request.setAttribute("model", detailDTO);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다. 로그인하세요.");
        boardService.글쓰기(saveDTO, sessionUser.getId());
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, BoardRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다. 로그인하세요.");
        boardService.글수정(id, updateDTO);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다. 로그인하세요.");
        boardService.글삭제(id);
        return "redirect:/";
    }
}