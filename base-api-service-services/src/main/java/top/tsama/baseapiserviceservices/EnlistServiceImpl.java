package top.tsama.baseapiserviceservices;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.EnlistService;
import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedao.mapper.EnlistMapper;
import top.tsama.baseapiservicedao.mapper.ExpertsinfoMapper;
import top.tsama.baseapiservicedomain.model.Enlist;
import top.tsama.baseapiservicedomain.model.EnlistVoinfo;

import java.util.Date;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/2.
 */
@Service
public class EnlistServiceImpl implements EnlistService{
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private EnlistMapper enlistMapper;

    public int deleteByCidAndUid(EnlistVoinfo enlistVoinfo) {
        try {
            int flag=enlistMapper.deleteByCidAndUid(enlistVoinfo);
            if(flag==1){
                return  1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("删除报名失败EnlistServiceImpl"+e.getMessage());
        }
        return 0;
    }

    @Override
    public int insert(Enlist record) {
        record.setCreatetime(new Date());
        record.setResult(0);
       try {
           int flag=enlistMapper.insert(record);
           if(flag==1){
               return  1;
           }
       }
      catch (Exception e){
           e.printStackTrace();
           logger.error("插入报名失败EnlistServiceImpl"+e.getMessage());
      }
       return 0;
    }

    @Override
    public List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo, Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try {
            List<EnlistVoinfo> enlistVoinfoList = enlistMapper.selectAll(enlistVoinfo);
            if (enlistVoinfoList != null) {
                return enlistVoinfoList;
            }
        }
        catch (Exception e){
            logger.error("查询报名失败"+e.getMessage());
            e.printStackTrace();
        }
            return null;
    }
}
