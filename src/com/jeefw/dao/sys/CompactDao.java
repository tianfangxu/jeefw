package com.jeefw.dao.sys;

import com.jeefw.model.sys.Compact;
import core.dao.Dao;

/**
 * 合同的数据持久层的接口
 * @JC
 */
public interface CompactDao extends Dao<Compact> {

    String getMaxNumber(String number);

}
