package com.yygh.cmn.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yygh.cmn.listener.DictListener;
import com.yygh.cmn.mapper.DictMapper;
import com.yygh.cmn.service.DictService;
import com.yygh.model.cmn.Dict;
import com.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Cacheable(value="dict", keyGenerator = "keyGenerator")
    //根据数据id查询子数据列表
    @Override
    public List<Dict> findChlidData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = dictMapper.selectList(wrapper);
        for (Dict dict : dictList) {
            Long dicId = dict.getId();
            Boolean isChild = this.isChildren(dicId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }


    //判断id下面是否有子数据
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = dictMapper.selectCount(wrapper);
        return count > 0;
    }

    @Override
    public void exportDictData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "dict";
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //查询数据库
            List<Dict> dictList = dictMapper.selectList(null);
            //dict =>list
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());

            for (Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo);
                dictVoList.add(dictVo);
            }

            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CacheEvict(value = "dict", allEntries=true)
    @Override
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}