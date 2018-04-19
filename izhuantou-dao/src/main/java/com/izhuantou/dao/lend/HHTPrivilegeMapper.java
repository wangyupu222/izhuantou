package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.HHTPrivilege;

/**
 * 加息券
 * 
 * @author yangbosen
 *
 */
public interface HHTPrivilegeMapper extends Mapper<HHTPrivilege> {

    public List<HHTPrivilege> findByOID(String mainOID);
}
