package life.tain.community.service;

import life.tain.community.dto.PaginationDTO;
import life.tain.community.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tian
 * @date 2020/7/2
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;


    public PaginationDTO list(Long id, Integer page, Integer size) {
        return null;
    }
}
