CREATE DATABASE IF NOT EXISTS `wjgtemp` DEFAULT CHARACTER SET utf8;

USE `wjgtemp`;

/*Table structure for table `REGULAR_ASSET` */
DROP TABLE IF EXISTS `REGULAR_ASSET`;
CREATE TABLE `REGULAR_ASSET` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `CODE` varchar(64) NULL DEFAULT NULL ,
    `NAME` varchar(64) NULL DEFAULT NULL ,
    `YEAR_RATE` decimal(18,4) NULL DEFAULT NULL ,
    `YEAR_DAYS` int NULL DEFAULT NULL COMMENT '计息时的一年总天数',
    `INVERT_TERM` int NULL DEFAULT NULL ,
    `AMOUNT_START` decimal(18,4) NULL DEFAULT NULL ,
    `AMOUNT_INCREASE` decimal(18,4) NULL DEFAULT NULL ,
    `TOTAL_AMOUNT` decimal(18,4) NULL DEFAULT NULL ,
    `REST_AMOUNT` decimal(18,4) NULL DEFAULT NULL ,
    `CAL_ACCRUAL_TYPE` varchar(64) NULL DEFAULT NULL COMMENT 'T+1',
    `REDEEM_TYPE` varchar(64) NULL DEFAULT NULL COMMENT 'T+1',
    `COLLECT_START_DAY` date NULL DEFAULT NULL ,
    `COLLECT_END_DAY` date NULL DEFAULT NULL ,
    `USER_TYPE` tinyint(4) NULL DEFAULT NULL COMMENT '1：个人用户 2：商户',
    `RISK_LEVEL` tinyint(4) NULL DEFAULT NULL COMMENT '10:保守型 20:稳健型 30:积极型',
    `IS_ASSIGNMENT_BOND` tinyint(1) NULL DEFAULT 0 ,
    `CATEGORY` tinyint(4) NULL DEFAULT NULL COMMENT '20:小贷类 21:信托类 22:委托债权类 23:承兑汇票类 24:信用证类 25:应收账款类 26:收益权类 27:回购股权融资类 28:其它',
    `PUBLISHER_TYPE` tinyint(4) NULL DEFAULT NULL COMMENT '40:交易所类 41:私募基金类 42:小贷公司类 43:银行类 44:证券公司类 45:其它',
    `STATE` tinyint(4) NULL DEFAULT NULL COMMENT '1：生效 -1：失效',
    `CREATE_TIME` timestamp DEFAULT NULL COMMENT '创建时间',
    `UPDATE_TIME` timestamp DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `REGULAR_CAPITAL` */
DROP TABLE IF EXISTS `REGULAR_CAPITAL`;
CREATE TABLE `REGULAR_CAPITAL` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `ASSET_SERIAL` varchar(64) NULL DEFAULT NULL COMMENT '用户资产序列号',
    `USER_ID` varchar(64) NULL DEFAULT NULL COMMENT '用户id',
    `PRODUCT_CODE` varchar(64) NULL DEFAULT NULL COMMENT '产品编码',
    `PRODUCT_NAME` varchar(64) NULL DEFAULT NULL COMMENT '产品名称',
    `INVERST_TERM` int NULL DEFAULT NULL COMMENT '投资期限',
    `INVERST_BALANCE` decimal(18,4) NULL DEFAULT NULL COMMENT '投资金额',
    `INVERST_DAY` date NULL DEFAULT NULL COMMENT '投资日期',
    `START_ACCRUAL_DAY` date NULL DEFAULT NULL COMMENT '起息日期',
    `END_ACCRUAL_DAY` date NULL DEFAULT NULL COMMENT '结息日期',
    `EXPECTED_ACCRUAL` decimal(18,4) NULL DEFAULT NULL COMMENT '预期收益',
    `CREATE_TIME` timestamp DEFAULT NULL COMMENT '创建时间',
    `UPDATE_TIME` timestamp DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

