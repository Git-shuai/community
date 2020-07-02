package life.tain.community.advice;

import com.alibaba.fastjson.JSON;
import life.tain.community.dto.ResultDTO;
import life.tain.community.exception.CustomizeErrorCode;
import life.tain.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tian
 * @date 2020/6/25
 */
@ControllerAdvice
public class CustomizeExceptionHandler {


    @ExceptionHandler(CustomizeException.class)
    Object handle(Throwable e, Model model, HttpServletRequest request,
                  HttpServletResponse response) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {

            ResultDTO resultDTO=null;
            if (e instanceof CustomizeException) {
                resultDTO= ResultDTO.errorOf((CustomizeException)e) ;
            } else {
                resultDTO= ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR) ;
            }

            try {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            return null;

        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");

        }

    }

}
