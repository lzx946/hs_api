package top.tsama.baseapiserviceservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.LoginRegisterService;
import top.tsama.baseapiservicedao.mapper.ExpertsinfoMapper;
import top.tsama.baseapiservicedao.mapper.StudentsinfoMapper;
import top.tsama.baseapiservicedomain.model.Expertsinfo;
import top.tsama.baseapiservicedomain.model.Studentsinfo;

import java.util.Date;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private StudentsinfoMapper studentsinfoMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Override
    public Studentsinfo selectByAccountEmail(Studentsinfo record) {
        try {
            Studentsinfo studentsinfo = studentsinfoMapper.selectByAccountEmail(record);
            if (studentsinfo != null) {
                return studentsinfo;
            }
        } catch (Exception e) {
            log.error("查询邮箱账户密码失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean register(Studentsinfo record) {
        try {
            int flag = 0;
            record.setPhotoid(1);
            record.setCreatetime(new Date());
            record.setState(0);
            flag = studentsinfoMapper.insert(record);
            if (flag != 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("注册失败");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Expertsinfo selectByAccountEmail(Expertsinfo record) {
        try {
            Expertsinfo expertsinfo = expertsinfoMapper.selectByAccountEmail(record);
            if (expertsinfo != null) {
                return expertsinfo;
            }
        } catch (Exception e) {
            log.error("查询邮箱账户密码失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean register(Expertsinfo record) {
        try {
            int flag = 0;
            record.setPhotoid(1);
            record.setCreatetime(new Date());
            record.setState(0);
            flag = expertsinfoMapper.insert(record);
            if (flag != 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("专家注册失败");
            e.printStackTrace();
        }
        return false;
    }
}
