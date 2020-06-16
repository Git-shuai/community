package life.tain.community.service;

import life.tain.community.dto.QuestionDTO;
import life.tain.community.mapper.QuestionMapper;
import life.tain.community.mapper.UserMapper;
import life.tain.community.model.Question;
import life.tain.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tian
 * @date 2020/6/16
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list() {

        List<Question> list = questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : list) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }


        return questionDTOList;
    }
}
