package life.tain.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tian
 * @date 2020/6/10
 */
@Controller
public class IndexController {
    @GetMapping("/hello")
    public String index(){
        return "index";
    }
}
