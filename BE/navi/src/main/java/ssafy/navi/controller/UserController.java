package ssafy.navi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @GetMapping("")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

}
