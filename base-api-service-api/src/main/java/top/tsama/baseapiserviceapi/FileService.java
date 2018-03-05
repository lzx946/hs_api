package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicedomain.model.SysFile;

/**
 * Created by wangdaren on 2018/2/2.
 */
public interface FileService {
    int insert(SysFile record);
    int deleteByPrimaryKey(Integer id);
    SysFile selectByPrimaryKey(Integer id);
}
