define(function (require, exports, module) {
    var ctx = '';
    var url = {
        URL_HSQ_CURRENT_HOME: ctx + '/insurance/init.do',
        URL_HSQ_CURRENT_DETAIL: ctx + '/insurance/detail.do',
        URL_HSQ_CURRENT_BUY_CHECK: ctx + '/insurancePurchase/isNewUser.do',
        URL_HSQ_CURRENT_PURCHASE_INIT: ctx + '/insurancePurchase/purchaseInit.do',
        URL_HSQ_CURRENT_OPEN_ACCOUNT: ctx + '/insurancePurchase/purchaseFormInputSubmit.do',
        URL_HSQ_CURRENT_MOBILE_CAPTCHA: ctx + '/insurancePurchase/sendVerficateCode.do',
        URL_HSQ_CURRENT_BANK_PAY: ctx + '/insurancePurchase/payOrderWithBank.do',
        URL_HSQ_CURRENT_BALANCE_PAY: ctx + '/insurancePurchase/payOrderWithBalance.do',
        URL_HSQ_CURRENT_SELL_INIT: ctx + '/insuranceSurrender/surrenderInit.do',
        URL_HSQ_CURRENT_SELL_SUBMIT: ctx + '/insuranceSurrender/surrender.do',
        URL_HSQ_CURRENT_QUERY: ctx + '/insuranceWorth/queryTradeDetail.do',
        URL_HSQ_CURRENT_RISK_INIT: ctx + '/insurancePurchase/queryRiskAssessmentExists.do',
        URL_HSQ_CURRENT_RISK_TEST: ctx + '/insurancePurchase/saveRiskAssessment.do'
    };
    var dummy = {
        URL_HSQ_CURRENT_HOME: {
            "current": [
                {"annualProfitRate": "4.05", "id": "3", "increaseAmount": "100", "leastAmount": "100", "millionRevenue": "1.0890", "productCode": "2016082601", "productName": "国华华瑞8号"},
                {"annualProfitRate": "3.05", "id": "3", "increaseAmount": "100", "leastAmount": "100", "millionRevenue": "1.0890", "productCode": "2016082602", "productName": "国华华瑞9号"}
            ],
            "dayFund": [
                {"dwjz": 0.627, "fcode": "530002", "ftype": null, "id": null, "minrg": 100, "risklevel": "低风险", "shortname": "建信货币", "syi": 2.972},
                {"dwjz": 0.6682, "fcode": "000588", "ftype": "货币型", "id": 43, "minrg": 100, "risklevel": "1", "shortname": "招商招钱宝货币A", "syi": 2.525},
                {"dwjz": 0.6022, "fcode": "000009", "ftype": "货币型", "id": 44, "minrg": 1000, "risklevel": "1", "shortname": "易方达天天理财货币A", "syi": 2.488},
                {"dwjz": 0.7147, "fcode": "020007", "ftype": "货币型", "id": 45, "minrg": 1000, "risklevel": "1", "shortname": "国泰货币", "syi": 2.505},
                {"dwjz": 0.6631, "fcode": "482002", "ftype": "货币型", "id": 46, "minrg": 1000, "risklevel": "1", "shortname": "工银货币", "syi": 2.465},
                {"dwjz": 0.4289, "fcode": "213009", "ftype": "货币型", "id": 47, "minrg": 1000, "risklevel": "1", "shortname": "宝盈货币A", "syi": 2.254},
                {"dwjz": 0.7558, "fcode": "202301", "ftype": "货币型", "id": 48, "minrg": 100, "risklevel": "1", "shortname": "南方现金增利货币A", "syi": 2.478},
                {"dwjz": 0.6054, "fcode": "050003", "ftype": "货币型", "id": 49, "minrg": 5000, "risklevel": "1", "shortname": "博时现金收益货币A", "syi": 2.291},
                {"dwjz": 0.6247, "fcode": "660007", "ftype": "货币型", "id": 50, "minrg": 1000, "risklevel": "1", "shortname": "农银货币A", "syi": 2.314},
                {"dwjz": 0.6415, "fcode": "270004", "ftype": "货币型", "id": 51, "minrg": 100, "risklevel": "1", "shortname": "广发货币A", "syi": 2.324},
                {"dwjz": 0.627, "fcode": "530002", "ftype": "货币型", "id": 52, "minrg": 1000, "risklevel": "1", "shortname": "建信货币", "syi": 2.972},
                {"dwjz": 0.6005, "fcode": "200003", "ftype": "货币型", "id": 53, "minrg": 1000, "risklevel": "1", "shortname": "长城货币A", "syi": 2.41},
                {"dwjz": 0.5732, "fcode": "090022", "ftype": "货币型", "id": 54, "minrg": 1000, "risklevel": "1", "shortname": "大成现金增利货币A", "syi": 2.663},
                {"dwjz": 0.3997, "fcode": "150005", "ftype": "货币型", "id": 55, "minrg": 100, "risklevel": "1", "shortname": "银河银富货币A", "syi": 1.56},
                {"dwjz": 0.5827, "fcode": "040003", "ftype": "货币型", "id": 56, "minrg": 1000, "risklevel": "1", "shortname": "华安现金富利货币A", "syi": 2.279},
                {"dwjz": 0.5881, "fcode": "360003", "ftype": "货币型", "id": 57, "minrg": 100, "risklevel": "1", "shortname": "光大货币", "syi": 2.284},
                {"dwjz": 0.5561, "fcode": "110006", "ftype": "货币型", "id": 58, "minrg": 1000, "risklevel": "1", "shortname": "易方达货币A", "syi": 2.473}
            ], "retMsg": "我的活期理财查询"
        },
        URL_HSQ_CURRENT_DETAIL: {
            productCode: 'gh001',
            productName: '国华华瑞8号',
            annualProfitRate: '4.31',
            millionRevenue: '1.2340',
            leastAmount: '150',
            increaseAmount: '20'
        },
        URL_HSQ_CURRENT_BUY_CHECK: {
            flag: 1,
            productCode: 'gh001',
            productName: '国华那个华瑞',
            annualProfitRate: '3.04',
            millionRevenue: '2.1353',
            leastAmount: '150'
        },
        URL_HSQ_CURRENT_PURCHASE_INIT: {
            productInfo: {
                productName: '国华华瑞8号',
                annualProfitRate: '4.31',
                leastAmount: 150,
                increaseAmount: 20
            },
            memberInfo: {
                name: '宝付君',
                idCard: '3****************1',
                mobile: '13111111111',
                email: 'baofoo@baofoo.com',
                address: '浦东新区居里路99号',
                province_code: '310000',
                city_code: '310000'
            }
        },
        URL_HSQ_CURRENT_OPEN_ACCOUNT: {
            insuranceProductDto: {productName: '国华华瑞8号', annualProfitRate: '5.12'},
            payments: [
                {accountId: '000'},
                {accountId: '1234', accountCode: '3002', accountName: '工商银行', remark: '6666', limitPerTime: '5000', limitPerDay: '50000'}],
            amount: '1000'
        },
        URL_HSQ_CURRENT_MOBILE_CAPTCHA: {},
        URL_HSQ_CURRENT_BANK_PAY: {
            productName: '国华华瑞8号',
            name: '宝付君',
            idCard: '3****************1',
            amount: '2300'
        },
        URL_HSQ_CURRENT_BALANCE_PAY: {
            productName: '国华华瑞8号',
            name: '宝付君',
            idCard: '3****************1',
            amount: '2300'
        },
        URL_HSQ_CURRENT_SELL_INIT: {
            assetMoney: "2000",
            redeemMoney: "1600"
        },
        URL_HSQ_CURRENT_SELL_SUBMIT: {},
        URL_HSQ_CURRENT_QUERY: {
            assetMoney: "10000",
            totalProfitMoney: "2345",
            yesterdayProfitMoney: "1234",
            tradeProfitDays: [],
            tradeOrderList: []
        },
        URL_HSQ_CURRENT_RISK_INIT: {
            flag: 0
        },
        URL_HSQ_CURRENT_RISK_TEST: {}
    };
    var api = {};
    for (var key in url) {
        api[key] = {
            url: url[key],
            dummy: dummy[key]
        }
    }
    return api;
});