package life.tain.community.controller;

import life.tain.community.dto.FileDTO;
import life.tain.community.util.OSSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tian
 * @date 2020/7/2
 */
@Controller
public class FileController {

    @Autowired
    private OSSFile ossFile;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String oss="";
        FileDTO fileDTO= new FileDTO();;
        try {
            oss = ossFile.oss(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!("文件名为空".equals(oss))||"".equals(oss)){

            fileDTO.setSuccess(1);
            fileDTO.setUrl(oss);
        }
        return fileDTO;
    }

}
