package cn.lloml.destinyrecruit.controller;


import cn.lloml.destinyrecruit.common.CustomResponse;
import cn.lloml.destinyrecruit.common.ProjectResponseBody;
import cn.lloml.destinyrecruit.service.GameMapService;
import cn.lloml.destinyrecruit.service.GameModeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/game_info")
public class GameModeAndMapController {
    @Resource
    private GameModeService gameModeService;
    @Resource
    private GameMapService gameMapService;

    @GetMapping("/game_mode")
    public ResponseEntity<ProjectResponseBody> getGameModeList() {
        return CustomResponse.ok("查询成功", gameModeService.selectAll());
    }

    @GetMapping("/game_map")
    public ResponseEntity<ProjectResponseBody> getGameMaoList(){
        return CustomResponse.ok("查询成功", gameMapService.selectAll());
    }
}
