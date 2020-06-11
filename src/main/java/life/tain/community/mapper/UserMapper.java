package life.tain.community.mapper;

import life.tain.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
