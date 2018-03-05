package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.Enlist;
import top.tsama.baseapiservicedomain.model.EnlistVoinfo;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/2.
 */
public interface EnlistService {
    /**
     * 根据cid和uid删除一条信息
     * @param enlistVoinfo
     * @return
     */
    int deleteByCidAndUid(EnlistVoinfo enlistVoinfo);
    /**
     * 插入课程报名
     * @param record
     * @return
     */
    int insert(Enlist record);

    /**
     * 查询报名情况
     * @param enlistVoinfo
     * @return
     */
    List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo, Pagination pagination);
}
