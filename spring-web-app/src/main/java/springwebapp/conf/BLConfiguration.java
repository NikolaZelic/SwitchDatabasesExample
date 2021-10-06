package springwebapp.conf;

import cocktailsearch.*;
import cocktailsearch.database.CocktailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BLConfiguration {

    private final CocktailsRepository cocktailsRepository;

    public BLConfiguration(CocktailsRepository cocktailsRepository) {
        this.cocktailsRepository = cocktailsRepository;
    }

    @Bean
    public CreateCocktailService createCocktailService() {
        return CocktailServiceFactory.newCreateCocktailService(cocktailsRepository);
    }

    @Bean
    public SearchCocktailService searchCocktailService() {
        return CocktailServiceFactory.newSearchCocktailService(cocktailsRepository);
    }

    @Bean
    public EditCocktailService editCocktailService() {
        return CocktailServiceFactory.newEditCocktailService(cocktailsRepository);
    }

    @Bean
    public DeleteCocktailService deleteCocktailService() {
        return CocktailServiceFactory.newDeleteCocktailService(cocktailsRepository);
    }

}
