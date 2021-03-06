package org.hsq.wjg.demo.codeGenetor.service;

import org.hsq.wjg.demo.codeGenetor.entity.RegularAsset;

/**
  * RegularAsset 操作service
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularAssetService {
	/**
	  * 根据id查询 RegularAsset
	  * @param id
	  */
	RegularAsset queryById(Integer id);

	/**
	  * 新增 RegularAsset
	  */
	void add(RegularAsset regularAsset);

	/**
	  * 更新 RegularAsset
	  */
	void update(RegularAsset regularAsset);
}