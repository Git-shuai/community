package life.tain.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tian
 * @date 2020/7/2
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
 }
