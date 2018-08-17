package cn.maxlu.demo.springmvc.config;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 相当于web.xml
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private String contextRealPath;

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        contextRealPath = servletContext.getRealPath("/");
//        servletContext.setInitParameter("name", "max");
//        servletContext.addListener("");
//        servletContext.addFilter();
        super.onStartup(servletContext);
    }

    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
        charFilter.setEncoding("utf-8");
        charFilter.setForceEncoding(true);
        return new Filter[]{charFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File file = Paths.get(contextRealPath, "upload", "storage").toFile();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(file.getAbsolutePath(), 1024, 1024, 0);
        registration.setMultipartConfig(multipartConfigElement);
        super.customizeRegistration(registration);
    }
}
