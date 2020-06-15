package life.tain.community.mapper;

import life.tain.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
    @Insert("insert into question (title,description,gmt_creat,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreat},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
