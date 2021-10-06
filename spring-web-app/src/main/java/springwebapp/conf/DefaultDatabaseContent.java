package springwebapp.conf;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultDatabaseContent implements ApplicationRunner {

    private final CocktailsRepository cocktailsRepository;

    public DefaultDatabaseContent(CocktailsRepository cocktailsRepository) {
        this.cocktailsRepository = cocktailsRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cocktailsRepository.save(cocktail("Mojito", "Tequila", "Coke", "Apple juice"));
        cocktailsRepository.save(cocktail("Long Island", "Coke", "Rum"));
    }

    private Cocktail cocktail(String name, String ... ingredients) {
        return new Cocktail(name, Stream.of(ingredients).map(Ingredient::new).collect(Collectors.toList()));
    }

}
