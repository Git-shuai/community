package life.tain.community.mapper;

import life.tain.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param offset
     * @param size
     */
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);


    /**
     * 总数
     * @return
     */
    @Select("select count(1) from question")
    Integer count();

    /**
     * 根据ID查询信息
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from question where creator =#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    /**
     * 总数
     * @return
     */
    @Select("select count(1) from question where creator =#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    /**
     * 根据ID查询问起
     * @return
     * @param id
     */
    @Select("select * from question where id=#{id}")
    Question getById(@Param("id")Integer id);
}
