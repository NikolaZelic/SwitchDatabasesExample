package cocktailsearch;

import cocktailsearch.database.Cocktail;

public interface EditCocktailService {

    Cocktail editCocktail(String cocktailName, String newCocktailName, String... ingredients);

}