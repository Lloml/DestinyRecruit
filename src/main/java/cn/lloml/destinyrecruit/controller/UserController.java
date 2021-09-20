package cn.lloml.destinyrecruit.controller;


import cn.lloml.destinyrecruit.common.CustomResponse;
import cn.lloml.destinyrecruit.common.CustomResponseBody;
import cn.lloml.destinyrecruit.domain.User;
import cn.lloml.destinyrecruit.dto.FireTeamInsertDTO;
import cn.lloml.destinyrecruit.dto.UserDTO;
import cn.lloml.destinyrecruit.service.BungiePlatformService;
import cn.lloml.destinyrecruit.service.FireTeamService;
import cn.lloml.destinyrecruit.service.GameMapService;
import cn.lloml.destinyrecruit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private BungiePlatformService bungiePlatformService;
    @Resource
    private FireTeamService fireTeamService;
    @Resource
    private GameMapService gameMapService;

    @GetMapping
    public List<User> getUserList() {
        return userService.selectAll();
    }

    /**
     * 创建用户
     *
     * @param userDto       用户数据传输对象
     * @param bindingResult 验证结果
     * @return 返回
     */
    @PostMapping
    public ResponseEntity<CustomResponseBody> postUser(@Validated @RequestBody UserDTO userDto, BindingResult bindingResult) {
        if (null != bindingResult && bindingResult.hasErrors()) {
            return CustomResponse.badRequest(bindingResult.getFieldErrors());
        }
        if (userService.selectOneByBungieName(userDto.getBungieName()) != null) {
            return CustomResponse.conflict("此棒鸡名已存在");
        }
        var membershipId = bungiePlatformService.searchUser(userDto.getBungieName());
        if (membershipId == null) {
            return CustomResponse.badRequest("棒鸡名不存在!");
        }
        var user = new User();
        user.setBungieName(userDto.getBungieName());
        user.setDestinyMembershipId(membershipId);
        userService.insert(user);
        return CustomResponse.ok("用户创建成功", user);
    }

    @PostMapping("/set_bungieName")
    public ResponseEntity<CustomResponseBody> setBungieName(@RequestBody Map<String, String> body) {
        var bungieName = body.get("bungieName");
        if (bungieName == null) {
            return CustomResponse.badRequest("请求中必须包含棒鸡名");
        }
        var user = userService.selectOneByBungieName(bungieName);
        if (user == null) {
            var newUserDTO = new UserDTO();
            newUserDTO.setBungieName(bungieName);
            return postUser(newUserDTO, null);
        } else {
            return CustomResponse.ok("棒鸡名设置成功", user);
        }
    }

    @GetMapping("/bungie_name/{bungieName}")
    public ResponseEntity<CustomResponseBody> getUserByBungieName(@PathVariable String bungieName) {
        var user = userService.selectOneByBungieName(bungieName);
        if (user == null) {
            return CustomResponse.notFound("找不到这个棒鸡名的用户");
        } else {
            return CustomResponse.ok("查询成功", user);
        }
    }

    @GetMapping("/{userId}/fire_team")
    public ResponseEntity<CustomResponseBody> getFireTeamByUserId(@PathVariable String userId) {
        var fireTeam = fireTeamService.selectOneDTOByUserId(Long.valueOf(userId));
        if (fireTeam == null) {
            return CustomResponse.notFound("用户没有加入或者创建任何火力战队");
        } else {
            return CustomResponse.ok("查询成功", fireTeam);
        }
    }

    @PostMapping("/{userId}/fire_team")
    public ResponseEntity<CustomResponseBody> postFireTeam(@PathVariable String userId, @Validated @RequestBody FireTeamInsertDTO fireTeamInsertDTO, BindingResult bindingResult) {
        if (null != bindingResult && bindingResult.hasErrors()) {
            return CustomResponse.badRequest(bindingResult.getFieldErrors());
        }
        if (gameMapService.selectByPrimaryKey(fireTeamInsertDTO.getGameMapId()) == null) {
            return CustomResponse.badRequest("不存在的此id的地图！");
        }
        if (userService.selectByPrimaryKey(Long.valueOf(userId)) == null) {
            return CustomResponse.badRequest("不存在此id的用户！");
        }
        if (fireTeamService.selectOneDTOByUserId(Long.valueOf(userId)) != null) {
            return CustomResponse.badRequest("用户已经加入或者创建了一个火力战队");
        }
        fireTeamService.userCreateFireTeamByDTO(fireTeamInsertDTO, Long.valueOf(userId));
        return CustomResponse.ok("火力战队创建完成", fireTeamService.selectOneDTOByUserId(Long.valueOf(userId)));
    }
}
