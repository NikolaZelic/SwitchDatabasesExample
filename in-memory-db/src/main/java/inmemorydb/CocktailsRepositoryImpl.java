package inmemorydb;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.MissingParameterException;

import java.util.*;
import java.util.stream.Collectors;

public class CocktailsRepositoryImpl implements CocktailsRepository {

    private final Map<String, Cocktail> cocktailsByName = new HashMap<>();

    @Override
    public Cocktail save(Cocktail cocktail) {
        if(cocktail == null)
            throw new MissingParameterException("cocktail");
        cocktailsByName.put(cocktail.getName(), cocktail);
        return cocktail;
    }

    @Override
    public void deleteCocktail(Cocktail cocktail) {
        if(cocktail == null)
            throw new MissingParameterException("cocktail");
        cocktailsByName.remove(cocktail.getName());
    }

    @Override
    public List<Cocktail> findAll() {
        return new ArrayList<>(cocktailsByName.values());
    }

    @Override
    public Cocktail findByName(String name) {
        return cocktailsByName.get(name);
    }

    @Override
    public List<Cocktail> findByIngredients(String... ingredients) {
        final List<Cocktail> result = new ArrayList<>();
        final Set<Ingredient> ingredientsCollection = Arrays.stream(ingredients).map(Ingredient::new).collect(Collectors.toSet());
        for (Map.Entry<String, Cocktail> entry : cocktailsByName.entrySet()) {
            final Cocktail cocktail = entry.getValue();
            if (cocktail.getIngredients().containsAll(ingredientsCollection)) {
                result.add(cocktail);
            }
        }
        return result;
    }

}
