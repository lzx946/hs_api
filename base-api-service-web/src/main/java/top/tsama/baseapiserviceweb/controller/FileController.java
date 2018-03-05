package top.tsama.baseapiserviceweb.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.tsama.baseapiserviceapi.FileService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.JsonUtil;
import top.tsama.baseapiservicecommon.webUtil;
import top.tsama.baseapiservicedomain.model.SysFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangdaren on 2018/1/23.
 */
@RestController
@RequestMapping("file")
public class FileController {
   Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    public FastFileStorageClient fastFileStorageClient;
    @Autowired
    public FileService fileService;

    /**
     * 上传文件（图片）
     * @param uploadFile
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("upload.do")
    public String uploadImg(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        try
        {
            String filename=uploadFile.getOriginalFilename();
            String strs= filename.substring(filename.lastIndexOf(".") + 1);
            if(!StringUtils.hasText(strs)){
                return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "请上传图片", null);
            }
            StorePath storePath = fastFileStorageClient.uploadFile((InputStream)uploadFile.getInputStream(),uploadFile.getSize(), FilenameUtils.getExtension(uploadFile.getOriginalFilename()),null);
            SysFile fileinfo=new SysFile();
            fileinfo.setUrl(storePath.getFullPath());
            Integer flag= fileService.insert(fileinfo);
            if(flag!=0){
                logger.info("上传文件成功"+storePath.getFullPath());
                fileinfo.setCode(1);
                fileinfo.setId(fileinfo.getId());
                fileinfo.setUrl(ActionUtil.ROOTURL+storePath.getFullPath());
            }
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "上传图片成功", fileinfo);
        }
        catch (Exception e){
            logger.error("上传文件失败"+e.getMessage());
            e.printStackTrace();
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "上传文件失败", null);
    }
    /**
     * 删除图片
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("delete.do")
    public String deleteImg(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String id=request.getParameter("id");
        if(Integer.parseInt(id)==1){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "删除成功", 1);
        }
        int flag=fileService.deleteByPrimaryKey(Integer.parseInt(id));
        if(flag==1) {
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "删除成功", flag);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "删除失败", flag);
    }
}

