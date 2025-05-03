package com.example.bullsandcows.controller;

import com.example.bullsandcows.service.NumberBaseball;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class NumberBaseballController {
    // NumberBaseball.java 가져오기
    private final NumberBaseball numberBaseball;
    public NumberBaseballController(NumberBaseball numberBaseball) {
        this.numberBaseball = numberBaseball;
    }

    // 초기 페이지 경로
    @GetMapping("/bullsandcows")
    public String index() {
        return "index";
    }
    // 제출 버튼 -> 게임 진행
    @PostMapping("/play")
    public String guess(@RequestParam(name = "number1") int number1, //index의 각 input태그로부터 파라미터 가져옴
                        @RequestParam(name = "number2") int number2,
                        @RequestParam(name = "number3") int number3,
                        Model model) {
        List<Integer> input = Arrays.asList(number1, number2, number3); // input 태그 값을 배열로
        // NumberBaseball play 메소드(스트라이크/볼)을 GameResult 클래스 result에 넣어줌
        NumberBaseball.GameResult result = numberBaseball.play(input); // GameResult rest = new GameResult(strikes, balls)

        model.addAttribute("setBase", numberBaseball.getSetBase());
        model.addAttribute("result", result); // 게임 결과
        model.addAttribute("history", numberBaseball.getGamHistory()); // 게임 진행
        model.addAttribute("finished", numberBaseball.isGameEnd()); // 게임 끝 메세지 출력
        return "index";
    }
    // 리셋 -> 게임 초기화
    @GetMapping("/restart")
    public String restart() {
        numberBaseball.clearGame();
        return "redirect:/bullsandcows";
    }
}
