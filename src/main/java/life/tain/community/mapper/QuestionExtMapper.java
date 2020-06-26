package life.tain.community.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
    void incView(@Param("id") Integer id);
}