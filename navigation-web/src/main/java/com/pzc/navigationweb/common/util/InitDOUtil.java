package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.domain.dbdo.BaseDO;
import com.pzc.navigationweb.domain.dbdo.BaseDOInt;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.basedto.ReqDTO;

import java.util.Date;

/**
 * @author ryf
 * @date 5/11/21 4:59 PM
 */
public class InitDOUtil {

    private static final String ID = "00000000000";
    private static final String ACCOUNT = "pzc";
    private static final String NAME = "痞子橙导航系统";

    public static void initField(BaseDOInt doInt){

        doInt.setId(ObjectId.get().toHexString());
        doInt.setCreateDate(new Date());
        doInt.setModifyDate(new Date());
        doInt.setVersion(1);
        doInt.setIsDel(false);

        UserDO userDO = UserSessionUtil.getCurrentUserByKey();
        if (userDO != null) {
            doInt.setCreateId(userDO.getId());
            doInt.setCreateAccount(userDO.getAccount());
            doInt.setCreateName(userDO.getName());
            doInt.setModifyId(userDO.getId());
            doInt.setModifyAccount(userDO.getAccount());
            doInt.setModifyName(userDO.getName());
        } else {
            doInt.setCreateId(InitDOUtil.ID);
            doInt.setCreateAccount(InitDOUtil.ACCOUNT);
            doInt.setCreateName(InitDOUtil.NAME);
            doInt.setModifyId(InitDOUtil.ID);
            doInt.setModifyAccount(InitDOUtil.ACCOUNT);
            doInt.setModifyName(InitDOUtil.NAME);
        }

    }

    public static void getUpdateField(BaseDOInt doInt){
        UserDO userDO = UserSessionUtil.getCurrentUserByKey();
        doInt.setModifyId(userDO.getId());
        doInt.setModifyAccount(userDO.getAccount());
        doInt.setModifyName(userDO.getName());
        doInt.setModifyDate(new Date());
        doInt.setVersion(doInt.getVersion() + 1);
    }

    public static <T extends ReqDTO> void buildSysUser(T t) {
        t.setId(ObjectId.get().toHexString());
        t.setCreateDate(new Date());
        t.setModifyDate(new Date());
        t.setVersion(1);
        t.setIsDel(false);

        t.setCreateId(InitDOUtil.ID);
        t.setCreateAccount(InitDOUtil.ACCOUNT);
        t.setCreateName(InitDOUtil.NAME);
        t.setModifyId(InitDOUtil.ID);
        t.setModifyAccount(InitDOUtil.ACCOUNT);
        t.setModifyName(InitDOUtil.NAME);
    }

    public static <P extends ReqDTO, M extends BaseDO> void req2DO(P p, M m) {

        m.setId(p.getId());
        m.setCreateDate(p.getCreateDate());
        m.setModifyDate(p.getModifyDate());
        m.setVersion(p.getVersion());
        m.setIsDel(p.getIsDel());

        m.setCreateId(p.getCreateId());
        m.setCreateAccount(p.getCreateAccount());
        m.setCreateName(p.getCreateName());
        m.setModifyId(p.getModifyId());
        m.setModifyAccount(p.getModifyAccount());
        m.setModifyName(p.getModifyName());
    }
}
