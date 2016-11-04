package org.hsq.wjg.demo.codeGenetor.service;

import org.hsq.wjg.demo.codeGenetor.entity.RegularRedeem;

/**
  * RegularRedeem 操作service
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularRedeemService {
	/**
	  * 根据id查询 RegularRedeem
	  * @param id
	  */
	RegularRedeem queryById(Integer id);

	/**
	  * 新增 RegularRedeem
	  */
	void add(RegularRedeem regularRedeem);

	/**
	  * 更新 RegularRedeem
	  */
	void update(RegularRedeem regularRedeem);
}