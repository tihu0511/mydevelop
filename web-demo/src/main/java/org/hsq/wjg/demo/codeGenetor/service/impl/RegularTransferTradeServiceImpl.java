package org.hsq.wjg.demo.codeGenetor.service.impl;

import org.hsq.wjg.demo.codeGenetor.dao.IRegularTransferTradeDao;
import org.hsq.wjg.demo.codeGenetor.entity.RegularTransferTrade;
import org.hsq.wjg.demo.codeGenetor.service.IRegularTransferTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

/**
  * RegularTransferTrade 操作service
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public class RegularTransferTradeServiceImpl implements IRegularTransferTradeService {
	@Autowired
	private IRegularTransferTradeDao regularTransferTradeDao;

	/**
	  * 根据id查询 RegularTransferTrade
	  * @param id
	  */
	public RegularTransferTrade queryById(Integer id) {
		return regularTransferTradeDao.queryById(id);
	}

	/**
	  * 新增 RegularTransferTrade
	  */
	public void add(RegularTransferTrade regularTransferTrade) {
		regularTransferTrade.setCreateTime(new Date());
		regularTransferTradeDao.add(regularTransferTrade);
	}

	/**
	  * 更新 RegularTransferTrade
	  */
	public void update(RegularTransferTrade regularTransferTrade) {
		regularTransferTrade.setUpdateTime(new Date());
		regularTransferTradeDao.update(regularTransferTrade);
	}
}