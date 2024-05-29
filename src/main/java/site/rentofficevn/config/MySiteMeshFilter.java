package site.rentofficevn.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "web.jsp");
        builder.addDecoratorPath("/admin*", "admin.jsp");
        builder.addDecoratorPath("/login", "login.jsp");

        builder.addExcludedPath("/404-page.html");
        builder.addExcludedPath("/api*");
        builder.addExcludedPath("/access-denied");
        builder.addExcludedPath("/ckfinder*");
        builder.addExcludedPath("/logout");
    }
}
