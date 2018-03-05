package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.StudentsVoinfo;
import top.tsama.baseapiservicedomain.model.Studentsinfo;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
public interface StudentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Studentsinfo record);

    Studentsinfo selectByPrimaryKey(Integer id);

    /**
     * 查询学员信息
     * @param studentsVoinfo
     * @param pagination
     * @return
     */
    List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo,Pagination pagination);

    /**
     * 更新学员信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Studentsinfo record);
}
