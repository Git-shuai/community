package life.tain.community.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author FTS36
 */
@Mapper
public interface CommentExtMapper {
    /**
     * 回复数
     * @param id
     * @return
     */
    @Update("update comment set comment_count=comment_count+1 where id=#{id}")
    int incCommentCount(@Param("id") Long id);
}