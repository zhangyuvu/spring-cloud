package com.zy.springcloud.service.impl;

import com.zy.springcloud.dao.StorageDao;
import com.zy.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zy
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------> 减少 {} 号商品库存量 {}  begin",productId,count);
        storageDao.decrease(productId,count);
        log.info("------> 减少 {} 号商品库存量 {}  end",productId,count);
    }

}
