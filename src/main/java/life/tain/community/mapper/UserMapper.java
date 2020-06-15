package life.tain.community.mapper;

import life.tain.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    @Insert("insert into user (name,account_id,token,gmt_creat,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);
}
