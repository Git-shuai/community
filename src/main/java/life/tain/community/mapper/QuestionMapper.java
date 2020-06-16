package life.tain.community.mapper;

import life.tain.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tian
 * @date 2020/6/15
 */
@Mapper
public interface QuestionMapper {
    /**
     *
     * @param question
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from question")
    List<Question> list();
}
