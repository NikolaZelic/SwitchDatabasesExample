package cocktailsearch.services;

import cocktailsearch.DeleteCocktailService;
import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.exceptions.NotExitingCocktailException;

public class DeleteCocktailServiceImpl extends AbstractCocktailService implements DeleteCocktailService {

    public DeleteCocktailServiceImpl(CocktailsRepository cocktailsRepository) {
        super(cocktailsRepository);
    }

    @Override
    public void deleteCocktail(String cocktailName) {
        if(cocktailName == null)
            throw new MissingParameterException("cocktailName");
        final Cocktail foundCocktail = cocktailsRepository.findByName(cocktailName);
        if(foundCocktail == null)
            throw new NotExitingCocktailException(cocktailName);
        cocktailsRepository.deleteCocktail(foundCocktail);
    }

}
