package com.project.demo.controller;

import com.project.demo.entity.SystemUser;
import com.project.demo.service.SystemUserService;
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
 * 系统用户：(SystemUser)表控制层
 *
 */
@RestController
@RequestMapping("/system_user")
public class SystemUserController extends BaseController<SystemUser, SystemUserService> {

    /**
     * 系统用户对象
     */
    @Autowired
    public SystemUserController(SystemUserService service) {
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
