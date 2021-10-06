package cocktailsearch.database;

import java.util.List;

public interface CocktailsRepository {

    Cocktail save(Cocktail cocktail);

    void deleteCocktail(Cocktail cocktail);

    List<Cocktail> findAll();

    Cocktail findByName(String name);

    List<Cocktail> findByIngredients(String... ingredients);

}
