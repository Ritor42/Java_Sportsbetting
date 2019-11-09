package config;

import main.App;
import org.springframework.context.annotation.Bean;
import service.SportsBettingService;
import view.View;

public class AppConfig {
    @Bean
    public App app() {
        return new App(new SportsBettingService(),new View());
    }
}
