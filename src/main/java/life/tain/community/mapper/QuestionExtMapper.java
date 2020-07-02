package life.tain.community.mapper;


import life.tain.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author FTS36
 */
@Mapper
public interface QuestionExtMapper {

    /**
     * 更新阅读数
     * @param id
     */
    @Update("update question set view_count=view_count+1 where id=#{id}")
    void incView(@Param("id") Long id);


    /**
     * 更新回复数
     * @param id
     */
    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void incCommentCount(@Param("id") Long id);

    /**
     * 根据标签查询
     * @param question
     * @return
     */
    @Select("select * from question where id !=#{id} and tag regexp #{tag}")
    List<Question> selectRelated(Question question);
}























