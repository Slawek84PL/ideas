package pl.slawek.ideas;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ideas")
@Data
public class IdeasConfiguration {

    private String name;

    @Value("${paging.pageSize:2}")
    private int pagingPageSize;

    @Value("${host.name:http://localhost}")
    private String hostname;

    @Value("${host.port:8080}")
    private int port;

    public String getFullHost() {
        return hostname + ":" + port;
    }
}
