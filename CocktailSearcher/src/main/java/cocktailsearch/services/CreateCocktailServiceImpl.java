package cocktailsearch.services;

import cocktailsearch.database.Cocktail;
import cocktailsearch.CreateCocktailService;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.InvalidParameterException;
import cocktailsearch.exceptions.MissingParameterException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateCocktailServiceImpl extends AbstractCocktailService implements CreateCocktailService {

    public CreateCocktailServiceImpl(CocktailsRepository cocktailsRepository) {
        super(cocktailsRepository);
    }

    @Override
    public Cocktail createCocktail(String cocktailName, String... ingredients) {
        if(cocktailName == null || cocktailName.isEmpty())
            throw new MissingParameterException("cocktailName");
        if(ingredients == null || ingredients.length == 0)
            throw new MissingParameterException("ingredients");
        for (String ingredient : ingredients) {
            if(ingredient.isEmpty())
                throw new InvalidParameterException("ingredients", "One of the ingredients is empty.");
        }
        final Cocktail cocktail = new Cocktail(cocktailName, Stream.of(ingredients).map(Ingredient::new).collect(Collectors.toList()));
        return cocktailsRepository.save(cocktail);
    }

}
