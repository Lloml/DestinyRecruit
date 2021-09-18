package cn.lloml.destinyrecruit.controller;


import cn.lloml.destinyrecruit.common.CustomResponse;
import cn.lloml.destinyrecruit.common.CustomResponseBody;
import cn.lloml.destinyrecruit.domain.User;
import cn.lloml.destinyrecruit.dto.UserDTO;
import cn.lloml.destinyrecruit.service.BungiePlatformService;
import cn.lloml.destinyrecruit.service.UserService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private BungiePlatformService bungiePlatformService;

    @GetMapping
    public List<User> getUserList() {
        return userService.selectAll();
    }

    @PostMapping
    public ResponseEntity<CustomResponseBody<Object>> postUser(@Validated @RequestBody UserDTO userDto, BindingResult bindingResult) throws JSONException {
        if (null != bindingResult && bindingResult.hasErrors()) {
            return CustomResponse.badRequest(bindingResult.getFieldErrors());
        }
        if (userService.selectOneByBungieName(userDto.getBungieName()) != null) {
            return CustomResponse.conflict("此BungieName已存在");
        }
        var membershipId = bungiePlatformService.searchUser(userDto.getBungieName());
        if(membershipId == null){
            return CustomResponse.badRequest("BungieName不存在!");
        }
        var user = new User();
        user.setBungieName(userDto.getBungieName());
        user.setDestinyMembershipId(membershipId);
        userService.insert(user);
        return CustomResponse.ok("ok");
    }
}
