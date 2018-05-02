package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.ContentMappingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageMainContentMapping;

/**
 * 团标产品和标的对应关系映射表 环环投管理分配完成之后生成数据
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pPackageMainContentMappingMapper extends Mapper<WebP2pPackageMainContentMapping> {

    /**
     * 查询主产品 的 产品期限（月）： productTerm 年利率：yearRate 还款方式（文字）：repaymentType
     * 
     * @return
     */
    public ContentMappingDTO findBycontentOID(String contentOID);

}
