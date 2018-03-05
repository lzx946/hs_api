package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.ExpertsVoinfo;
import top.tsama.baseapiservicedomain.model.Expertsinfo;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
public interface ExpertService {
    /**
     * 查询专家信息
     * @param expertsVoinfo
     * @param pagination
     * @return
     */
    List<ExpertsVoinfo> selectAll(ExpertsVoinfo expertsVoinfo, Pagination pagination);

    /**
     * 更新专家信息
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(Expertsinfo record);
}
