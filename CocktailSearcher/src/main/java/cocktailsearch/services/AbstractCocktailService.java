package cocktailsearch.services;

import cocktailsearch.database.CocktailsRepository;

public abstract class AbstractCocktailService {

    protected final CocktailsRepository cocktailsRepository;

    public AbstractCocktailService(CocktailsRepository cocktailsRepository) {
        this.cocktailsRepository = cocktailsRepository;
    }

}
