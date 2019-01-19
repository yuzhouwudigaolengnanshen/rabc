package com.xingxue.rbac.listener;

import com.xingxue.rbac.entity.Module;
import com.xingxue.rbac.service.ModuleService;
import com.xingxue.rbac.service.impl.ModuleServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author yihang
 */
@WebListener
public class LoaderListener implements ServletContextListener{
    private ModuleService moduleService = new ModuleServiceImpl();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 应用程序一启动，就查询出所有的模块，放入application作用域
        List<Module> modules = moduleService.findAll();
        sce.getServletContext().setAttribute("modules", modules);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
