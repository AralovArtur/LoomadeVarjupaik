package ee.ut.loomadevp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

public class MvcConfiguration implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("et"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

//     регистр контроллеров, который отвечает за отображение шаблонов
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/avaleht").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/loomadvp").setViewName("loomad_vp");
        registry.addViewController("/viiols").setViewName("vii_ols");
        registry.addViewController("/viiolsreg").setViewName("vii ols reg");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/kontakt").setViewName("kontakt");
    }
}
