package org.example.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppSpringMvcDispatherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException, ServletException {
        super.onStartup(aServletContext);
//        registerCharacterEncodingFilter(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

//    private void registerCharacterEncodingFilter(ServletContext context) {
//        FilterRegistration.Dynamic characterEncodingFilter = context.addFilter("encodingFilter",
//                new CharacterEncodingFilter());
//        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
//        characterEncodingFilter.setInitParameter("forceEncoding", "true");
//        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
//    }
    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }



}
