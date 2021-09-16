package cn.lloml.destinyrecruit.controller;


import cn.lloml.destinyrecruit.domain.User;
import cn.lloml.destinyrecruit.service.UserService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getUserList(){
        return userService.selectAll();
    }

    @PostMapping
    @ResponseBody
    public void postUser(@Validated User user, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>(16);
        if (null != bindingResult && bindingResult.hasErrors()) {
            List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();
            System.out.println("the field error is " + fieldErrorsList);
            map.put("parameterErrors", fieldErrorsList);
        }
//        userService.insert(user);
    }
}
