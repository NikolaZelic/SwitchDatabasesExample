package cocktailsearch.services;

import cocktailsearch.SearchCocktailService;
import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.exceptions.MissingParameterException;

import java.util.Collections;
import java.util.List;

public class SearchCocktailServiceImpl extends AbstractCocktailService implements SearchCocktailService {

    public SearchCocktailServiceImpl(CocktailsRepository cocktailsRepository) {
        super(cocktailsRepository);
    }

    @Override
    public List<Cocktail> findAll() {
        final List<Cocktail> result = cocktailsRepository.findAll();
        if(result == null)
            return Collections.emptyList();
        return result;
    }

    @Override
    public Cocktail findByName(String name) {
        if(name == null || name.isEmpty())
            throw new MissingParameterException("name");
        return cocktailsRepository.findByName(name);
    }

    @Override
    public List<Cocktail> findByIngredients(String... ingredients) {
        if(ingredients == null || ingredients.length == 0)
            throw new MissingParameterException("ingredients");
        return cocktailsRepository.findByIngredients(ingredients);
    }

}
