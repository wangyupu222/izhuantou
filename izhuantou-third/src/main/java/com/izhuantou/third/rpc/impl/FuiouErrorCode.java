package com.izhuantou.third.rpc.impl;

public class FuiouErrorCode {
    /**
     * 富友的错误信息
     * 
     * @param code
     * @return
     */
    public static String errorCode(String code) {
	switch (code) {
	case "1000":
	    return "取系统跟踪号失败";
	case "1001":
	    return "无此用户";
	case "1002":
	    return "用户未激活";
	case "1003":
	    return "用户已锁定";
	case "1004":
	    return "用户已关闭";
	case "1005":
	    return "用户已禁用";
	case "1006":
	    return "未知的用户类型";
	case "1007":
	    return "用户未指定";
	case "1008":
	    return "实名信息不合法";
	case "1009":
	    return "取授权号失败";
	case "1014":
	    return "无效卡号(无此卡号";
	case "1042":
	    return "无此账户";
	case "1051":
	    return "资金不足";
	case "1101":
	    return "无此商户";
	case "1102":
	    return "商户（或机构）已关闭";
	case "1103":
	    return "商户已锁定";
	case "2000":
	    return "账户状态正常";
	case "2001":
	    return "无此账户";
	case "2002":
	    return "账户未激活";
	case "2003":
	    return "账户已锁定";
	case "2004":
	    return "账户已冻结";
	case "2005":
	    return "账户已销户";
	case "2006":
	    return "账户已过期";
	case "2007":
	    return "账户已挂失";
	case "2008":
	    return "账户状态不正常";
	case "2010":
	    return "分户状态正常";
	case "2011":
	    return "找不到分户";
	case "2012":
	    return "分户未激活";
	case "2013":
	    return "分户已锁定";
	case "2014":
	    return "分户已冻结";
	case "2015":
	    return "分户已销户";
	case "2016":
	    return "分户已过期";
	case "2017":
	    return "分户已挂失";
	case "2018":
	    return "分户状态不正常";
	case "2019":
	    return "账户币种与交易币种不符";
	case "2020":
	    return "未指定分户";
	case "2030":
	    return "贷记账户状态正常";
	case "2031":
	    return "无此贷记账户";
	case "2032":
	    return "贷记账户未激活";
	case "2033":
	    return "贷记账户已锁定";
	case "2034":
	    return "贷记账户已冻结";
	case "2035":
	    return "贷记账户已销户";
	case "2036":
	    return "贷记账户已过期";
	case "2037":
	    return "贷记账户已挂失";
	case "2038":
	    return "贷记账户状态不正常";
	case "2039":
	    return "借贷记账记不属于同一个机构";
	case "2040":
	    return "借记账户不属于发起交易的机构";
	case "2041":
	    return "贷记账户不属于发起交易的机构";
	case "2101":
	    return "账号长度非法";
	case "2102":
	    return "账号检验失败";
	case "2103":
	    return "账户查询密码未设置";
	case "2104":
	    return "账户支付密码未设置";
	case "2105":
	    return "查询密码错误";
	case "2106":
	    return "支付密码错误";
	case "2107":
	    return "查询密码错误次数超限";
	case "2108":
	    return "支付密码错误次数超限";
	case "2109":
	    return "未知的密码类型";
	case "3001":
	    return "功能暂不支持";
	case "3002":
	    return "未知的交易代码";
	case "3003":
	    return "验证MAC失败";
	case "3004":
	    return "不支持的交易";
	case "3005":
	    return "禁止账户使用的交易";
	case "3006":
	    return "禁止连接机构进行的交易";
	case "3007":
	    return "无法确定交易账户的分户";
	case "3011":
	    return "网关类交易金额超过上限";
	case "3012":
	    return "金额无效";
	case "3013":
	    return "账户未转结余额小于0";
	case "3014":
	    return "未转结余额不足（小于交易金额）";
	case "3015":
	    return "账户冻结余额小于0";
	case "3016":
	    return "已冻结余额不足（小于交易金额）";
	case "3017":
	    return "可用余额小于0";
	case "3018":
	    return "可用余额不足";
	case "3019":
	    return "交易金额小于下限";
	case "3020":
	    return "账户总余额不为0";
	case "3021":
	    return "账户总余额不足";
	case "3022":
	    return "交易金额不足以支付手续费";
	case "3023":
	    return "账户可用余额小于「交易金额+手续费」";
	case "3024":
	    return "作业类交易金额超过上限";
	case "3101":
	    return "找不到原交易(或原交易不成功";
	case "3102":
	    return "原交易不成功";
	case "3103":
	    return "原交易已冲正";
	case "3104":
	    return "原交易已撤消";
	case "3105":
	    return "原交易已完成";
	case "3106":
	    return "原交易已冻结";
	case "3107":
	    return "原交易已解冻";
	case "3108":
	    return "原交易金额不符";
	case "3109":
	    return "原交易账号不符";
	case "3110":
	    return "找不到原始授权交易";
	case "3111":
	    return "原交易清算日期非法";
	case "3112":
	    return "原交易不是清算交易";
	case "3120":
	    return "不需要记授权历史记录（参数错误）";
	case "3121":
	    return "与原授权交易商户不匹配";
	case "3122":
	    return "原授权交易已全部完成";
	case "3123":
	    return "交易卡号不匹配";
	case "3124":
	    return "交易金额超过可完成（解冻/撤消）的金额";
	case "3125":
	    return "交易金额与原授权金额不一致";
	case "3126":
	    return "终端号不一致";
	case "3127":
	    return "不在同一清算日内";
	case "3201":
	    return "清分记账失败";
	case "3251":
	    return "提现账户未指定";
	case "3252":
	    return "找商户附加信息错";
	case "3253":
	    return "提现账户用法错误(24域";
	case "3271":
	    return "清分超时";
	case "3272":
	    return "记账超时";
	case "4001":
	    return "找不到指定的账户产品";
	case "4002":
	    return "不允许开户机构使用的账户产品";
	case "4003":
	    return "找不到指定账户产品的BIN号";
	case "4004":
	    return "找不到指定科目的BIN号（或该科目不允许开分户）";
	case "4005":
	    return "某一BIN号的账户号资源已用完";
	case "4006":
	    return "记客户账号表出错";
	case "4007":
	    return "记客户内部账号表出错";
	case "4008":
	    return "记基本账户表出错";
	case "4009":
	    return "账户产品已过期，不能使用";
	case "4010":
	    return "企业用户不能开对私账户";
	case "4011":
	    return "个人用户不能开对公账户";
	case "4012":
	    return "记客户账户密码失败";
	case "4013":
	    return "个人客户账户已存在";
	case "5001":
	    return "session超时";
	case "5002":
	    return "验签失败";
	case "5110":
	    return "用户名或密码错误";
	case "5017":
	    return "修改用户信息时未做任何修改";
	case "5018":
	    return "根据地区代码和行别找不到对应支行";
	case "5019":
	    return "数据校验失败";
	case "5029":
	    return "调用交易查询接口过于频繁";
	case "5137":
	    return "账户信息不能修改";
	case "5138":
	    return "系统异常";
	case "5239":
	    return "商户不存在";
	case "5343":
	    return "用户已开户";
	case "5344":
	    return "账务系统开户失败";
	case "5345":
	    return "商户流水号重复";
	case "5346":
	    return "商户流水号不存在";
	case "5347":
	    return "与商户系统日期不一致";
	case "5348":
	    return "交易用户不存在";
	case "5349":
	    return "找不到原交易";
	case "5350":
	    return "指令提交模式只支持富友余额支付";
	case "5351":
	    return "商户提现流水号重复";
	case "5352":
	    return "未找到该商户交易记录";
	case "5353":
	    return "接收FAS报文出现异常";
	case "5354":
	    return "FAS报文验签失败";
	case "5355":
	    return "发送FAS通讯出现异常";
	case "5356":
	    return "该卡号已认证";
	case "5357":
	    return "该卡号已经受理且认证通过";
	case "5358":
	    return "该卡号已经受理,但尚未认证通过";
	case "5359":
	    return "该卡号尚未签约";
	case "5460":
	    return "发送日切通知失败";
	case "5836":
	    return "不允许信用卡注册";
	case "5837":
	    return "卡号和行别不一致";
	case "5850":
	    return "已经存在相同银行卡号注册的用户";
	case "5851":
	    return "已经存在相同证件号注册的用户";
	case "5852":
	    return "实名验证失败";
	case "5853":
	    return "商户IP不允许访问";
	case "5854":
	    return "协议库验证日期超过7天";
	case "5855":
	    return "该手机号绑定卡号超过2张";
	case "5856":
	    return "无权限访问该接口";
	case "5857":
	    return "实名验证失败,当日总验证次数超限";
	case "5891":
	    return "用户已开户";
	case "9000":
	    return "未知的操作方式，参数错误";
	case "9001":
	    return "未知的取流水号方式";
	case "9002":
	    return "找不到连接机构";
	case "9003":
	    return "连接机构已关闭";
	case "9004":
	    return "连接机构非法";
	case "9005":
	    return "未知的查找原交易方式";
	case "9006":
	    return "未知的余额检查类型";
	case "9007":
	    return "未知的交易日志更新方式";
	case "9008":
	    return "未知的授权历史日志更新方式";
	case "9009":
	    return "未知的原交易日志更新方式";
	case "9010":
	    return "未知的原授权历史日志更新方式";
	case "9011":
	    return "未知的原始交易数据用法";
	case "9012":
	    return "未知的账户资料更新方式";
	case "9013":
	    return "未知的授权历史日志检查方式";
	case "9014":
	    return "未知的原交易更新对象";
	case "9015":
	    return "未知的账户状态检查方式";
	case "9016":
	    return "系统异常";
	case "9701":
	    return "内存错误";
	case "9801":
	    return "数据库错误";
	case "9802":
	    return "提交数据库事务失败";
	case "9803":
	    return "数据库SELECT操作失败";
	case "9804":
	    return "数据库INSERT操作失败";
	case "9805":
	    return "数据库UPDATE操作失败";
	case "9806":
	    return "数据库事务回滚失败";
	case "9901":
	    return "SOTP同步调用失败";
	case "9902":
	    return "SOTP异步调用失败";
	case "210001":
	    return "发送kbpsDataBean失败";
	case "210002":
	    return "kbps没有初始化，无法连接";
	case "10029":
	    return "金额超限";
	case "100011":
	    return "卡号或者户名不符";
	case "5555":
	    return "交易确定超时";
	case "5556":
	    return "用户信息修改期间不能代扣充值或提现";
	case "5557":
	    return "用户未授权";
	case "5098":
	    return "订单支付失败，通讯异常";
	case "5901":
	    return "查询范围只允许在31填内或31天前";
	case "5013":
	    return "无此交易权限";
	case "5183":
	    return "商户手续费不足";
	case "5143":
	    return "验证码错误";
	case "5336":
	    return "原交易找不到";
	case "8010":
	    return "订单支付失败，获取验证码失败";
	case "8143":
	    return "订单支付失败，验证码失效或错误";
	case "5505":
	    return "不支持的银行卡";
	case "100016":
	    return "金额超限";
	case "100017":
	    return "余额不足";
	case "100029":
	    return "金额超限";
	case "100013":
	    return "账户不允许交易";
	case "999998":
	    return "认证失败";
	case "10XC        ":
	    return "户名或证件号码不符";
	case "1061":
	    return "超出取款/转账金额限制";
	case "1096":
	    return "系统异常、失效";
	case "10FC        ":
	    return "借记卡单笔交易金额超限";
	case "10FB":
	    return "同卡笔数超限";
	case "10HA":
	    return "商户电子凭条未经审核";
	case "10M2        ":
	    return "超出借记卡同商户当月累计金额限制";
	case "10SM        ":
	    return "超过金额限制";
	case "999999":
	    return "账户类型不支持";

	}
	return null;
    }

}
