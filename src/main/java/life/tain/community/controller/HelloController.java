package life.tain.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tian
 * @date 2020/6/10
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name ="name",required = false,defaultValue = "fengtian")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
