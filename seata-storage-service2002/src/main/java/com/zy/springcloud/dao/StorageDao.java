package com.zy.springcloud.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author zy
 */

public interface StorageDao {

    void decrease(@Param("productId") Long productId,
                  @Param("count") Integer count);

}
