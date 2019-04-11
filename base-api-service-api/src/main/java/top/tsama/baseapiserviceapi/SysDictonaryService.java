package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicedomain.model.SysDictionary;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/26.
 */
public interface SysDictonaryService {

    List<SysDictionary> selectAll(SysDictionary sysDictionary);
}
