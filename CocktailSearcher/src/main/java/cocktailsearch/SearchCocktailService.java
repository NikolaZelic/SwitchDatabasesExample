package cocktailsearch;

import cocktailsearch.database.Cocktail;

import java.util.List;

public interface SearchCocktailService {

    List<Cocktail> findAll();

    Cocktail findByName(String name);

    /**
     * Returned cocktails will contains all ingredients and it might contains some more.
     */
    List<Cocktail> findByIngredients(String... ingredients);

}
