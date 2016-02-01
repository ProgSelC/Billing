package ua.falbi.eugene;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Configuration
@ComponentScan("ua.falbi.eugene")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/pages/");
    }

    @Bean
    public EntityManager entityManager()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
        return emf.createEntityManager();
    }

    @Bean
    public MyDAO myDAO()
    {
        return new MyDAOImpl();
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver()
    {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();

        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);

        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        return new CommonsMultipartResolver();
    }
}
