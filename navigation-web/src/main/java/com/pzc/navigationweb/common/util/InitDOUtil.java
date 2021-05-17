package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.domain.dbdo.BaseDOInt;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;

import java.util.Date;

/**
 * @author ryf
 * @date 5/11/21 4:59 PM
 */
public class InitDOUtil {

    private static final String ACCOUNT = "pzc";

    public static void initField(BaseDOInt doInt){

        doInt.setId(ObjectId.get().toHexString());
        doInt.setCreateDate(new Date());
        doInt.setModifyDate(new Date());
        doInt.setVersion(1);
        doInt.setIsDel(false);

        UserDO userDO = UserSessionUtil.getCurreentUserByKey();
        if (userDO != null) {
            doInt.setCreateName(userDO.getAccount());
            doInt.setModifyName(userDO.getAccount());
        } else {
            doInt.setCreateName(InitDOUtil.ACCOUNT);
            doInt.setModifyName(InitDOUtil.ACCOUNT);
        }

    }
}
