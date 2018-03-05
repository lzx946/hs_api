package top.tsama.baseapiservicedao.mapper;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.ExpertsVoinfo;
import top.tsama.baseapiservicedomain.model.Expertsinfo;

import java.util.List;

@Component
public interface ExpertsinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Expertsinfo record);

    Expertsinfo selectByPrimaryKey(Integer id);

    List<ExpertsVoinfo> selectAll(ExpertsVoinfo expertsVoinfo);

    /**
     * 更新专家信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Expertsinfo record);

    /**
     * 根据账户或邮箱登录
     *
     * @param record
     * @return
     */
    Expertsinfo selectByAccountEmail(Expertsinfo record);

}