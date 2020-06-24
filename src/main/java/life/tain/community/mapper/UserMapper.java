package life.tain.community.mapper;

import life.tain.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author tian
 * @date 2020/6/11
 */
@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user (name,account_id,token,gmt_creat,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    /**
     * 查询
     * @param token
     * @return
     */
    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from user where id =#{id}")
    User findById(@Param("id") Integer id);

    /**
     * 查询
     * @param accountId
     * @return
     */
    @Select("select * from user where account_id =#{accountId}")
    User findByAcountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    void update(User user);
}
