package com.izhuantou.common.tool;

//用户显示信息隐藏
public class ToolsDisplay {
    // 手机号码显示隐藏中间5位数
    public static String getMobleStr(String str) {
	if (str == null)
	    return " ";
	str = str.substring(0, str.length() - (str.substring(3)).length()) + "*****" + str.substring(8);
	return str;
    }

    // 身份证号码隐藏
    public static String getIdentityCardStr(String identity_card) {
	if (identity_card == null || identity_card.equals("")) {
	    return " ";
	}

	// 6224 2619870205 3713
	identity_card = identity_card.substring(0, identity_card.length() - (identity_card.substring(4)).length())
		+ "**********" + identity_card.substring(14);
	return identity_card;
    }

    // 银行卡号码隐藏（显示前四位、后四位）
    public static String getBankCardNumberStr(String bank_card_number) {
	if (bank_card_number == null)
	    return " ";
	// {16,17,18,19}
	String replace_string = "********";
	switch (bank_card_number.length()) {
	case 16:
	    replace_string = "********";
	    break;
	case 17:
	    replace_string = "*********";
	    break;
	case 18:
	    replace_string = "**********";
	    break;
	case 19:
	    replace_string = "***********";
	    break;
	}
	bank_card_number = bank_card_number.substring(0,
		bank_card_number.length() - (bank_card_number.substring(4)).length()) + replace_string
		+ bank_card_number.substring(replace_string.length() + 4);
	return bank_card_number;
    }

    // 姓名隐藏
    public static String getFullNameStr(String full_name) {
	if (full_name == null)
	    return " ";
	String replace_string = "*";
	for (int i = 1; i < full_name.length() - 1; i++) {
	    replace_string = replace_string + "*";
	}
	full_name = full_name.substring(0, 1) + replace_string;
	return full_name;
    }
}
