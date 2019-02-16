package com.example.controller;

import com.example.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(value = "Retrieve list of games")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of games")})
    @GetMapping(value = "/list")
    public List<String> gameUrlList() {
        return gameService.gameUrlList();
    }

}
