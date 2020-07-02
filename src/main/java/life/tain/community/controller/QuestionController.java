package life.tain.community.controller;

import life.tain.community.dto.CommentDTO;
import life.tain.community.dto.QuestionDTO;
import life.tain.community.enums.CommentTypeEnum;
import life.tain.community.service.CommentService;
import life.tain.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author tian
 * @date 2020/6/23
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model){

        //累加阅读数
        questionService.incView(id);

        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        QuestionDTO questionDTO=questionService.getById(id);
        List<QuestionDTO> selectRelatedList =questionService.selectRelated(questionDTO);

        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("selectRelatedList",selectRelatedList);

        return "question";
    }

}
