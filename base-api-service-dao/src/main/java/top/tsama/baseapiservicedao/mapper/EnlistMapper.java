package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.Enlist;
import top.tsama.baseapiservicedomain.model.EnlistVoinfo;

@Component
public interface EnlistMapper {
    int deleteByCidAndUid(EnlistVoinfo enlistVoinfo);

    int insert(Enlist record);

    Enlist selectByPrimaryKey(Integer id);

    List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo);

    int updateByPrimaryKey(Enlist record);
}