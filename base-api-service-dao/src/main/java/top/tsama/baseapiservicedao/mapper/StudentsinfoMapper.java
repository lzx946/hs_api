package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.StudentsVoinfo;
import top.tsama.baseapiservicedomain.model.Studentsinfo;
@Component
public interface StudentsinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Studentsinfo record);

    Studentsinfo selectByPrimaryKey(Studentsinfo studentsinfo);

    List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo);

    /**
     * 更新学员信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Studentsinfo record);

    /**
     * 根据账户或邮箱登录
     * @param record
     * @return
     */
    Studentsinfo selectByAccountEmail(Studentsinfo record);
}