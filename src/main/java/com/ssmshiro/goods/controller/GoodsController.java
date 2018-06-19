package com.ssmshiro.goods.controller;

import com.ssmshiro.base.controller.BaseController;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.response.ResultEntity;
import com.ssmshiro.goods.dao.GoodsDao;
import com.ssmshiro.goods.entity.Goods;
import com.ssmshiro.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController<GoodsService,GoodsDao,Goods> {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResultEntity<String> save(@RequestBody Goods goods){
        goodsService.save(goods);
        return new ResultEntity<String>().OK(InfoCode.IMSG0001);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ResultEntity<Goods> getById(@PathVariable Long id){
        return new ResultEntity<Goods>().OK(goodsService.get(id),InfoCode.IMSG0004);
    }
}
