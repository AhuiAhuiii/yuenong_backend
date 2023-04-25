package ltd.newbee.mall.api.mall;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import ltd.newbee.mall.util.AliOSSUtils;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ltd.newbee.mall.util.Result;

import java.io.IOException;

@Slf4j
@RestController
@Api(value = "v1", tags = "客户端害虫识别功能oss图片上传接口")
@RequestMapping("/api/v1")
public class uploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    Result result = ResultGenerator.genSuccessResult();

    @PostMapping("/newupload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}",image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url:{}",url);
        result.setData(url);
        return result;
    }
}
