package inmemorydb;

import cocktailsearch.database.CocktailsRepository;

public final class CocktailsRepositoryFactory {

    public static CocktailsRepository newCocktailsRepository() {
        return new CocktailsRepositoryImpl();
    }

}
