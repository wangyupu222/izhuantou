package com.izhuantou.common.rsa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos 钥匙对存储类 版本号构成 pkversion_XX
 */

public class RsaEnum {
	/**
	 * @author Carlos 私钥 版本号 pkversion_01 对应公钥
	 *         "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3z0ORehjlT8+U+zCF/
	 *         OVYOJK5fAPZbxre6Crz m/n2uPZa86GtqpVx8htp7WXYp/
	 *         CP21gtvBSyqHAOLVxjy7Nb9NeF8x7o5mHTtLYRpW6yfFUgV13g
	 *         8+eNVq7R3vPs+YbKPQLYzFOvdvRZIhKTJJ9Re1AEDBKzuj0q+aXMbLp4HwIDAQAB"
	 */
	public static String pkversion_01 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfPQ5F6GOVPz5T7MIX85Vg4krl8"
			+ "A9lvGt7oKvOb+fa49lrzoa2qlXHyG2ntZdin8I/bWC28FLKocA4tXGPLs1v014XzHujmYdO0thGl"
			+ "brJ8VSBXXeDz541WrtHe8+z5hso9AtjMU6929FkiEpMkn1F7UAQMErO6PSr5pcxsungfAgMBAAEC"
			+ "gYBhqsDlYPIvjnvnVp1O5LTcbpsP8eR9uXqWrNh9vwhP37IPW9sx0y07cYC9hUYTnBDH763oB0Mc"
			+ "N0HrVpD7+fXf5MRK4NXgVWf4aRW/Nl3BCRuThA6u/withCarUUFeZEbvaHt+G1TKB8u2uosj307x"
			+ "4VN8zu+wrzHT+RoSh/4XEQJBAOZnIqTh0vg1skkN20Mgo6p4lfI4XxMwJQ6iy8QL9Itxk0HWTk4W"
			+ "DlVcvP2z0sL6ly5rW+CBrUKW0EnVrFAuh0cCQQDMOvmL6W6fqfPCm+lH8Vw/E+Kbphdk2L/h+lcT"
			+ "HasJUkGbuSuOwL13gNiP1bPURNG0lxkV5eh1pX4uHj6p6SRpAkEA1G8/iBsXM/f+fFEkW05a2TD5"
			+ "wC2tq7l2UXpUulvzjCaaCdgYtvKrIJWzQ5BrwY7fmdMvEV8/aug2//zdNcjQvQJAcvF6Q7GErl4D"
			+ "sHL5j6EbDvFNJ9cJ8ZtAG5o+6aZX/e4KuO81waleuWSN/h4DkSOOdzpXRwjXlWcoJiKlmBF8iQJA"
			+ "KzyHBR3vSLGMz7PdPFH7s964XPEKyvBd168A5DrsF03pvWXbWZvOfFlzQZoa4Tzr4mEFTR1O1JiE8uVdF+JfZw==";
	/**
	 * @author Carlos 私钥 版本号 pkversion_02 对应公钥
	 *         "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDmxmhRQ4m5tGULnXNL/
	 *         FkdTYe4bVgEsnCq4X2G
	 *         YDYEQ9wfIAScPekpy1FdfYxsoYfgjxD2FnHWADTFuz6pQLMeegUG7NoY8wZSRUeFV3MpBrLizgva
	 *         Dp0HbU1BCvwpy89V4aOMr5iN4UnpCr7dsmZ5Qg72EIh1deAwi0kIMZD4NwIDAQAB"
	 */
	public static String pkversion_02 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAObGaFFDibm0ZQudc0v8WR1Nh7ht"
			+ "WASycKrhfYZgNgRD3B8gBJw96SnLUV19jGyhh+CPEPYWcdYANMW7PqlAsx56BQbs2hjzBlJFR4VX"
			+ "cykGsuLOC9oOnQdtTUEK/CnLz1Xho4yvmI3hSekKvt2yZnlCDvYQiHV14DCLSQgxkPg3AgMBAAEC"
			+ "gYEA3cOYB0+BkAU8fLbnTwPGlxQj8iSRSqI3Bt79gx39ioezhe18wK/12gXZwJ+fS9zF+BNDV1gm"
			+ "OGGmVMUk5/yWokW1hoU8LnalmuVwXBECbLCGdARHgZ+GFgVZDtMeyTp/98UwvmgYjW97nEAQp3Bt"
			+ "q1btVZt1TXPglyQ99XTfySECQQDztOr/d+5Yyt2da6D1mzR5F70SPzkQBa4XfmKk5FuB1JcZrU2w"
			+ "MnCxV+w7karZJ46WKb/Qvy195imhOz7V6XivAkEA8mp/YFYYSFkwNNuFKP9zgS3U4z0x3zQBHutU"
			+ "nzkMGETUyr1+UUMmSM9lbuyFD6Viy5lTcsGtEPvcNor6muhK+QJAUJI4uztIVpzDRf9GWMz2PS6m"
			+ "+LhkQjLOir/jFwbUZgkF55xPOfiDH26lEFCpfl/AmU62NuptvNjyXHa84HXuywJALaBLzycFNQDM"
			+ "aPZ0Tq4d4E9vBUIsU9Vm7JngeIN1OlAiOPKHhuX7m7KlZcZQmQxI1wdJxIyddVfiL8XiuytDiQJB"
			+ "AKEL/IegXnkQuePHeNHkH0qDo8mKOLPvm6T4oJ8A+jETPQbmNRsRrsShBTmTLWnts+4ZC65eLXJYHORAqtNV2zk=";

	/**
	 * @author Carlos 私钥 版本号 存储器
	 */
	public static Map<String, Object> pkversion_map = null;

	/**
	 * @author Carlos 私钥 存储器初始化
	 */
	RsaEnum() throws Exception {
		pkversion_map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
//			System.out.println(fields[i].getName() + "      " + fields[i].get(fields[i].getName()));
			pkversion_map.put(fields[i].getName(), fields[i].get(fields[i].getName()));
		}
	}

}
