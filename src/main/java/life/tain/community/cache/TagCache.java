package life.tain.community.cache;

import life.tain.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tian
 * @date 2020/7/2
 */
public class TagCache {
    public static List<TagDTO> getTag(){

        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","java","php","css","c","c++","node","html5","shell","python","golong","c#","mysql"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("系统框架");
        framework.setTags(Arrays.asList("Spring","SpringMVC","SpringBoot","Mybatis","JDBC","SpringCloud","Vue"));
        tagDTOS.add(framework);

        return tagDTOS;
    }

}
