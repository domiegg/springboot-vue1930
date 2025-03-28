package com.project.demo.controller;

import com.project.demo.entity.MemorandumInformation;
import com.project.demo.service.MemorandumInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 备忘录信息：(MemorandumInformation)表控制层
 *
 */
@RestController
@RequestMapping("/memorandum_information")
public class MemorandumInformationController extends BaseController<MemorandumInformation, MemorandumInformationService> {

    /**
     * 备忘录信息对象
     */
    @Autowired
    public MemorandumInformationController(MemorandumInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
