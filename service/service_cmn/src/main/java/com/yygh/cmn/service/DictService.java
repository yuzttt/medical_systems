package com.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
public interface DictService extends IService<Dict>  {
    List<Dict> findChlidData(Long id);

    void exportDictData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
