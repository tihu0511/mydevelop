package org.hsq.wjg.demo.codeGenetor.service.impl;

import org.hsq.wjg.demo.codeGenetor.dao.IRegularRedeemDao;
import org.hsq.wjg.demo.codeGenetor.entity.RegularRedeem;
import org.hsq.wjg.demo.codeGenetor.service.IRegularRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

/**
  * RegularRedeem 操作service
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public class RegularRedeemServiceImpl implements IRegularRedeemService {
	@Autowired
	private IRegularRedeemDao regularRedeemDao;

	/**
	  * 根据id查询 RegularRedeem
	  * @param id
	  */
	public RegularRedeem queryById(Integer id) {
		return regularRedeemDao.queryById(id);
	}

	/**
	  * 新增 RegularRedeem
	  */
	public void add(RegularRedeem regularRedeem) {
		regularRedeem.setCreateTime(new Date());
		regularRedeemDao.add(regularRedeem);
	}

	/**
	  * 更新 RegularRedeem
	  */
	public void update(RegularRedeem regularRedeem) {
		regularRedeem.setUpdateTime(new Date());
		regularRedeemDao.update(regularRedeem);
	}
}