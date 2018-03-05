package top.tsama.baseapiserviceservices;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.FileService;
import top.tsama.baseapiservicedao.mapper.SysFileMapper;
import top.tsama.baseapiservicedomain.model.SysFile;

/**
 * Created by wangdaren on 2018/2/2.
 */
@Service
public class FileServiceImpl implements FileService {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    public FastFileStorageClient fastFileStorageClient;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Override
    public int insert(SysFile record) {
        int flag=sysFileMapper.insert(record);
        if(flag==1){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            SysFile sysFiles=sysFileMapper.selectByPrimaryKey(id);
            fastFileStorageClient.deleteFile(sysFiles.getUrl());
            int flag=sysFileMapper.deleteByPrimaryKey(id);
            if(flag==1){
                return 1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 0;
    }

    @Override
    public SysFile selectByPrimaryKey(Integer id) {
        SysFile sysFile=sysFileMapper.selectByPrimaryKey(id);
        if(sysFile!=null){
            return sysFile;
        }
        else
        {
            return null;
        }
    }
}
