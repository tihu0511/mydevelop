package org.hsq.wjg.demo.codeGenetor.service.impl;

import org.hsq.wjg.demo.codeGenetor.dao.IRegularProductDao;
import org.hsq.wjg.demo.codeGenetor.entity.RegularProduct;
import org.hsq.wjg.demo.codeGenetor.service.IRegularProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

/**
  * RegularProduct 操作service
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public class RegularProductServiceImpl implements IRegularProductService {
	@Autowired
	private IRegularProductDao regularProductDao;

	/**
	  * 根据id查询 RegularProduct
	  * @param id
	  */
	public RegularProduct queryById(Integer id) {
		return regularProductDao.queryById(id);
	}

	/**
	  * 新增 RegularProduct
	  */
	public void add(RegularProduct regularProduct) {
		regularProduct.setCreateTime(new Date());
		regularProductDao.add(regularProduct);
	}

	/**
	  * 更新 RegularProduct
	  */
	public void update(RegularProduct regularProduct) {
		regularProduct.setUpdateTime(new Date());
		regularProductDao.update(regularProduct);
	}
}