package com.izhuantou.admin.controller.accountManagement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.vo.UrgentNoticeDTO;
import com.izhuantou.service.api.accoutManagement.UrgentNoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	private UrgentNoticeService urgentNoticeService;
    
    /**
     * 按条件查询紧急公告信息列表
     * @param currentPage 页数
     * @param urgentNoticeDTO 紧急公告DTOBean
     * @return
     */
	@SystemControllerLog(description = "按条件查询紧急公告信息列表")
    @RequestMapping(value = "/geturgentnoticelist")
    @ResponseBody
    public OpResult getUrgentNoticeListByTypes(Integer currentPage,UrgentNoticeDTO urgentNoticeDTO) {
		Map<String,Object> urgentNoticeList = urgentNoticeService.findUrgentNotice(currentPage,urgentNoticeDTO);
		if (urgentNoticeList == null) {
		    return OpResult.getFailedResult("没有数据了");
		} else {
		    return OpResult.getSuccessResult(urgentNoticeList);
		}
    }
    
    /**
	 * 添加一条紧急公告
	 * @param UrgentNoticeDTO 紧急公告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条紧急公告")
	@RequestMapping(value = "/addurgentnotice")
	@ResponseBody
	public OpResult addUrgentNotice(UrgentNoticeDTO urgentNoticeDTO){
		if(StringUtil.isNotEmpty(urgentNoticeDTO.getName()) && StringUtil.isNotEmpty(urgentNoticeDTO.getMessages())
				&& StringUtil.isNotEmpty(urgentNoticeDTO.getType()) && StringUtil.isNotEmpty(urgentNoticeDTO.getStartTime())
				&& StringUtil.isNotEmpty(urgentNoticeDTO.getEndTime())){
			String num = urgentNoticeService.addUrgentNotice(urgentNoticeDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条紧急公告
	 * @param OID 紧急公告oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条紧急公告")
	@RequestMapping(value = "/deleteurgentnotice")
	@ResponseBody
	public OpResult deleteUrgentNotice(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = urgentNoticeService.deleteUrgentNotice(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条紧急公告
	 * @param UrgentNoticeDTO 紧急公告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条紧急公告")
	@RequestMapping(value = "/updateurgentnotice")
	@ResponseBody
	public OpResult updateHelpCentreProblem(UrgentNoticeDTO urgentNoticeDTO){
		if(StringUtil.isNotEmpty(urgentNoticeDTO.getOID())){
			String num = urgentNoticeService.updateUrgentNotice(urgentNoticeDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
}
