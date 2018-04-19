package com.izhuantou.service.impl.user;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.sequence.SequenceDefinition;
import com.izhuantou.dao.sequence.SequenceDefinitionMapper;
import com.izhuantou.service.api.user.SequenceDefinitionService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("sequenceDefinitionService")
public class SequenceDefinitionServiceImpl extends BaseServiceImpl<SequenceDefinition>
	implements SequenceDefinitionService {
    private static final Logger logger = LoggerFactory.getLogger(SequenceDefinitionServiceImpl.class);

    @Autowired
    private SequenceDefinitionMapper sequenceDefinitionMapper;

    /**
     * 获取序列号
     */
    @Override
    public String gainSequence(String name) {
	try {
	    // 获取是那种协议
	    SequenceDefinition sequence = sequenceDefinitionMapper.findSequenceDefinitionByName(name);

	    Date creatDateTime = sequence.getCreatDateTime();
	    Date date = new Date();
	    if (creatDateTime != null) {
		String clearTime = sequence.getClearTime();
		String nowDate = DateUtils.formatDate(date.getTime(), clearTime);
		String creatTime = DateUtils.formatDate(creatDateTime, clearTime);
		if (!nowDate.equals(creatTime)) {
		    sequence.setValue(0);
		}
	    }
	    sequence.setCreatDateTime(date);
	    sequence.setValue(sequence.getValue() + 1);
	    int row = sequenceDefinitionMapper.updSequenceDefinitionByName(sequence);
	    if (row == 1) {
		SequenceDefinition seq = sequenceDefinitionMapper.findSequenceDefinitionByName(name);
		String strFormat = seq.getFormat();
		if (strFormat.contains("!:-")) {

		    String strHead = strFormat.substring(0, strFormat.indexOf("!:-"));
		    String strDate = DateUtils.formatDate(date,
			    strFormat.substring(strFormat.indexOf("!:-") + 3, strFormat.indexOf("-:!")));
		    String strBoot = strFormat.substring(strFormat.indexOf("-:!") + 3, strFormat.length());
		    strFormat = strHead + strDate + strBoot;
		} else {

		    strFormat = DateUtils.formatDate(date, strFormat);
		}
		String strSequence = (seq.getValue()).toString();
		int iFormat = strFormat.indexOf("#");
		int iCount = 0;
		while (iFormat != -1) {
		    iCount++;
		    iFormat = strFormat.indexOf("#", iFormat + 1);
		}
		strFormat = strFormat.replaceAll(ToolString.addLengthAtLeft("#", "#", iCount),
			ToolString.addLengthAtLeft(strSequence, "0", iCount));

		return strFormat;
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("gainSequence(String name)", e.getMessage());
	    return null;
	}

    }

}
