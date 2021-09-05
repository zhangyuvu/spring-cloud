package com.zy.springcloud.dao;

import com.zy.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author zy
 */
public interface OrderDao {

    void create(Order order);

    void update(@Param("userId")Long userId, @Param("status") Integer status);

}
