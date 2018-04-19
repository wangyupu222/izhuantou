package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.damain.lend.DDTPrivilege;
import com.izhuantou.damain.lend.HHTPrivilege;

public interface PrivilegeService {
    /** 根据OID查询环环投加息券 */
    public List<HHTPrivilege> findPrivilege(String OID, String memberOID);

    /** 根据OID查询点点投加息券 */
    public List<DDTPrivilege> findDDTPrivilege(String OID, String memberOID);
}
