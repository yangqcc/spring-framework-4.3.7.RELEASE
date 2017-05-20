package com.yqc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by yangqc on 2017/4/12.
 */
@WebListener
public class MyDataContextListener implements ServletContextListener {

    private ServletContext context = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.context = servletContextEvent.getServletContext();
        context.setAttribute("myData", "this is MyData!");
    }

    /**
     * 关闭的时候调用
     *
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.context = null;
    }
}
