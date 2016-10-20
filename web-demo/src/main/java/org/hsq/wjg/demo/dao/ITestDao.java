package org.hsq.wjg.demo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by wujigang on 2016/10/10.
 */
public interface ITestDao {
    String queryMemberBank(@Param("memberId") Integer memberId);
}
