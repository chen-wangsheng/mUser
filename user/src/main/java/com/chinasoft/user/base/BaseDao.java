package com.chinasoft.user.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 统一配置DAO
 * @author vanceChen
 * @param <T>
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
