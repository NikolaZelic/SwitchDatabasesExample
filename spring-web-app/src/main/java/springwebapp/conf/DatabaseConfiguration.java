package springwebapp.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sqldb.SQLDbConfiguration;

@Configuration
@Import({
        SQLDbConfiguration.class
})
public class DatabaseConfiguration {

}
