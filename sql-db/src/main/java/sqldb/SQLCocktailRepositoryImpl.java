package sqldb;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SQLCocktailRepositoryImpl implements CocktailsRepository {

    private final JPACocktailRepository repository;

    public SQLCocktailRepositoryImpl(JPACocktailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cocktail save(Cocktail cocktail) {
        return convertToDto(repository.save(convertToEntity(cocktail)));
    }

    @Override
    public void deleteCocktail(Cocktail cocktail) {
        repository.delete(convertToEntity(cocktail));
    }

    @Override
    public List<Cocktail> findAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Cocktail findByName(String name) {
        return repository.findById(name).map(this::convertToDto).orElse(null);
    }

    @Override
    public List<Cocktail> findByIngredients(String... ingredients) {
        return repository.findByIngredients(Arrays.asList(ingredients))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CocktailEntity convertToEntity(Cocktail cocktail) {
        return new CocktailEntity(cocktail.getName(),
            cocktail.getIngredients().stream()
                .map(Ingredient::getName)
                .map(IngredientEntity::new)
                .collect(Collectors.toList()));
    }

    private Cocktail convertToDto(CocktailEntity entity) {
        return new Cocktail(entity.getName(),
            entity.getIngredients().stream()
                    .map(IngredientEntity::getName)
                    .map(Ingredient::new)
                    .collect(Collectors.toList()));
    }

}
