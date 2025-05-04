package com.example.bullsandcows.controller;

import com.example.bullsandcows.DTO.RankingForm;
import com.example.bullsandcows.Entity.Ranking;
import com.example.bullsandcows.Repository.RankingRepository;
import com.example.bullsandcows.service.NumberBaseball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class NumberBaseballController {
    // 의존성 주입
    @Autowired
    RankingRepository rankingRepository;
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

//        // dto가 저장되는지 확인위한 로그
//        System.out.println(rankingForm.toString());
//
//        // dto -> entity
//        Ranking ranking = rankingForm.toEntity();
//        System.out.println(ranking.toString());
//
//        // entity-> repository
//        Ranking saved = rankingRepository.save(ranking);
//        System.out.println(saved.toString());

        // dto -> enity-> repository
        if(numberBaseball.isGameEnd()) { // 게임이 끝날 경우 DB에 저장된다.
            Ranking ranking = new Ranking();
            ranking.setTryCount(numberBaseball.getTryCount());
            rankingRepository.save(ranking);

            // 마찬가지로 게임이 끝난 경우 DB에 저장된 데이터를 가져와 보여 줄 수 있게 한다
            //  Repo에서 entity리스트로 데이터 가져오기
            List<Ranking> rankingEntityList = rankingRepository.findAll();
            // model에 등록하기
            model.addAttribute("rankinglist", rankingEntityList);
        }

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

    // DB내용 페이지를 따로출력 할 경우
//    @GetMapping("/allresult")
//    public String index(Model model) {
//        //  Repo에서 entity리스트로 데이터 가져오기
//        List<Ranking> rankingEntityList = rankingRepository.findAll();
//        // model에 등록하기
//        model.addAttribute("rankinglist", rankingEntityList);
//        return "index";
//    }

}
