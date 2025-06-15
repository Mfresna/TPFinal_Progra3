package TpFinal_Progra3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Esto sirve archivos desde /uploads/imagenes/ dentro del proyecto
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:uploads/img/");
    }

}