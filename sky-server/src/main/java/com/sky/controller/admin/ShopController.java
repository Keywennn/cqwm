package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminController")
@RequestMapping("admin/shop")
@Slf4j
@Api("店铺相关接口")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("店铺营业状态修改")
    public Result setStatus(@PathVariable("status") Integer status) {
        log.info("店铺营业状态修改为：{}", status);
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        Integer i = (Integer) redisTemplate.opsForValue().get(KEY); // 这里需要进行类型强转
        log.info("店铺的营业状态为{}",i);
        return Result.success(i);
    }
}
