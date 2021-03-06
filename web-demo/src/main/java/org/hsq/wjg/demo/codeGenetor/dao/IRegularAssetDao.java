package org.hsq.wjg.demo.codeGenetor.dao;

import org.hsq.wjg.demo.codeGenetor.entity.RegularAsset;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
  * RegularAsset 数据库操作
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularAssetDao {
    List<RegularAsset> query(RegularAsset $entityNameVar);

    RegularAsset queryById(@Param("id") Integer id);

    void add(RegularAsset $entityNameVar);

    void update(RegularAsset $entityNameVar);
}