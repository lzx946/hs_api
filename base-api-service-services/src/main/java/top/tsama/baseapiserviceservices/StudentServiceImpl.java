package top.tsama.baseapiserviceservices;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.StudentService;
import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedao.mapper.StudentsinfoMapper;
import top.tsama.baseapiservicedomain.model.Expertsinfo;
import top.tsama.baseapiservicedomain.model.StudentsVoinfo;
import top.tsama.baseapiservicedomain.model.Studentsinfo;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Service
public class StudentServiceImpl implements StudentService {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private StudentsinfoMapper studentsinfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Studentsinfo record) {
        return 0;
    }

    @Override
    public Studentsinfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo,Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try{
           List<StudentsVoinfo> studentsVoinfoList=studentsinfoMapper.selectAll(studentsVoinfo);
           if(studentsVoinfoList!=null){
               logger.info("StudentServiceImpl查询学员列表成功");
               return studentsVoinfoList;
           }
       }
       catch (Exception e){
            e.printStackTrace();
            logger.error("StudentServiceImpl查询学员列表失败"+e.getMessage());
       }
       return null;
    }

    @Override
    public int updateByPrimaryKey(Studentsinfo record) {
       try {
            int flag=studentsinfoMapper.updateByPrimaryKey(record);
            if(flag==1){
                return 1;
            }
       }
       catch (Exception  e){
           logger.error("更新学员信息失败"+e.getMessage());
           e.printStackTrace();
       }
       return 0;
    }
}
