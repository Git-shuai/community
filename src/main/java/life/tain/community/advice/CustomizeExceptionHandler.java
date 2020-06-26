package life.tain.community.advice;

import life.tain.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tian
 * @date 2020/6/25
 */
@ControllerAdvice
public class CustomizeExceptionHandler {


    @ExceptionHandler(CustomizeException.class)
    ModelAndView handle(Throwable e, Model model) {

        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器跑丢了");
        }
        return new ModelAndView("error");
    }

}
