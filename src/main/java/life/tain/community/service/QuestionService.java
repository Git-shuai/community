package life.tain.community.service;

import life.tain.community.dto.PaginationDTO;
import life.tain.community.dto.QuestionDTO;
import life.tain.community.mapper.QuestionMapper;
import life.tain.community.mapper.UserMapper;
import life.tain.community.model.Question;
import life.tain.community.model.QuestionExample;
import life.tain.community.model.User;
import life.tain.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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


    public PaginationDTO list(Integer page, Integer size) {

        if (page < 1) {
            page = 1;
        }
        Integer offset = size * (page - 1);

        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : list) {
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        if (page < 1) {
            page = 1;
        }
        Integer offset = size * (page - 1);


        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> list = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : list) {
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = questionMapper.countByExample(example);
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(example);
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //更新
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        } else {
            //修改


            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());

            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }
    }
}
