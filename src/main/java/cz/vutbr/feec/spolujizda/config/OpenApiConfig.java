package cz.vutbr.feec.spolujizda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpolujízdaNaPožádání API")
                        .version("1.0.0")
                        .description("REST API dokumentace pro projekt SpolujízdaNaPožádání (EBC-WAB). " +
                                "Zahrnuje správu jízd, chytré přepočítávání tras (SmartRoutingAgent), " +
                                "doporučování podle preferencí a pohlaví (RecommendationAgent) " +
                                "a meteorologické notifikace (WeatherNotificationAgent).")
                        .contact(new Contact()
                                .name("Tým EBC-WAB")
                                .email("student@vut.cz"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
