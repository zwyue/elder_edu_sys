package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.PowerService;
import com.zhu.base.dao.PowerDao;
import com.zhu.base.entity.Power;
import com.zhu.base.vo.PowerVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * PowerServiceImpl class
 *
 * @author zwy
 * @date 2018/12/1 10:46
 */
@Service
public class PowerServiceImpl implements PowerService {

    private final static Logger logger = LoggerFactory.getLogger(PowerServiceImpl.class);

    @Autowired
    private PowerDao powerDao ;

    @Override
    public Map<String,List<Power>> queryPowerByPowerIds(List<String> powerIds) {
        logger.info("***********查询权限***********");
        List<Power> powers = powerDao.queryPowerByPowerIds(powerIds);
        List<Power> menuPowers = powers.stream().filter(power -> SysConstant.ZERO .equals (power.getCategory())
                && SysConstant.PowerType.MENU.equals(power.getTypes())).collect(Collectors.toList());
        //只有两级菜单的子节点
        menuPowers.forEach(menu->{
            menu.setLeaf(true);
            List<Power> powerList = new ArrayList<>();
            powers.forEach(power -> {
                if( power.getCategory().equals(String.valueOf(menu.getId())) &&
                        SysConstant.PowerType.MENU.equals(power.getTypes())){
                    power.setLeaf(true);
                    menu.setLeaf(false);
                    powerList.add(power);
                }
            });
            menu.setChildPowers(powerList);
        });

        Map<String,List<Power>> map = new HashMap<>();
        map.put("allRights",powers);
        map.put("menuRights",menuPowers);
        return map ;
    }

    @Override
    @PagingQuery
    public List<PowerVo> queryPowerList(String powerIds) {
        List<String> powerList = StringUtils.isNotBlank(powerIds)?Arrays.asList(powerIds.split(SysConstant.Punctuation.COMMA)):new ArrayList<>(SysConstant.MAP_DEFAULT_SIZE);
        List<Power> powers = powerDao.queryPowerList(powerList);
        List<PowerVo> powerVos = new ArrayList<>();
        powers.forEach(power -> {
            PowerVo powerVo = new PowerVo();
            powerVo.setId(power.getId());
            powerVo.setName(power.getPowername());
            powerVo.setpId(Integer.parseInt(power.getCategory()));
            powerVos.add(powerVo);
        });
        return powerVos ;
    }
}
