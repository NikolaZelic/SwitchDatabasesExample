package cocktailsearch.services;

import cocktailsearch.database.Cocktail;
import cocktailsearch.EditCocktailService;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.InvalidParameterException;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.exceptions.NotExitingCocktailException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditCocktailServiceImpl extends AbstractCocktailService implements EditCocktailService {

    public EditCocktailServiceImpl(CocktailsRepository cocktailsRepository) {
        super(cocktailsRepository);
    }

    @Override
    public Cocktail editCocktail(String cocktailName, String newCocktailName, String... ingredients) {
        if(cocktailName == null)
            throw new MissingParameterException("cocktailName");
        if(ingredients == null || ingredients.length == 0)
            throw new MissingParameterException("ingredients");
        for (String ingredient : ingredients) {
            if(ingredient.isEmpty())
                throw new InvalidParameterException("ingredients", "One of the ingredients is empty.");
        }
        final Cocktail cocktail = cocktailsRepository.findByName(cocktailName);
        if(cocktail == null)
            throw new NotExitingCocktailException(cocktailName);
        cocktail.setIngredients(Stream.of(ingredients).map(Ingredient::new).collect(Collectors.toList()));
        if(newCocktailName != null) {
            cocktailsRepository.deleteCocktail(cocktail);
            cocktail.setName(newCocktailName);
        }
        return cocktailsRepository.save(cocktail);
    }

}
