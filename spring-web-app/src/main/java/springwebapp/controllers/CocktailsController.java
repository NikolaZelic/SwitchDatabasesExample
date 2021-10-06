package springwebapp.controllers;

import cocktailsearch.CreateCocktailService;
import cocktailsearch.DeleteCocktailService;
import cocktailsearch.EditCocktailService;
import cocktailsearch.SearchCocktailService;
import cocktailsearch.database.Cocktail;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailsController {

    private final SearchCocktailService searchCocktailService;

    private final CreateCocktailService createCocktailService;

    private final EditCocktailService editCocktailService;

    private final DeleteCocktailService deleteCocktailService;

    public CocktailsController(SearchCocktailService searchCocktailService, CreateCocktailService createCocktailService, EditCocktailService editCocktailService, DeleteCocktailService deleteCocktailService) {
        this.searchCocktailService = searchCocktailService;
        this.createCocktailService = createCocktailService;
        this.editCocktailService = editCocktailService;
        this.deleteCocktailService = deleteCocktailService;
    }



    @GetMapping
    public Collection<Cocktail> getCocktails() {
        return searchCocktailService.findAll();
    }

    @GetMapping("/{name}")
    public Cocktail getCocktailByName(@PathVariable String name) {
        return searchCocktailService.findByName(name);
    }

    @GetMapping("/search")
    public Collection<Cocktail> searchCocktails(@RequestParam String[] values) {
        return searchCocktailService.findByIngredients(values);
    }

    @PostMapping
    public Cocktail createCocktail(@RequestBody CreateCocktailDTO cocktail) {
        return createCocktailService.createCocktail(cocktail.getName(), cocktail.getIngredients());
    }

    @PutMapping("/{name}")
    public Cocktail editCocktail(@PathVariable String name, @RequestBody CreateCocktailDTO cocktail) {
        return editCocktailService.editCocktail(name, cocktail.getName(), cocktail.getIngredients());
    }

    @DeleteMapping("/{name}")
    public void deleteCocktail(@PathVariable String name) {
        deleteCocktailService.deleteCocktail(name);
    }

    static class CreateCocktailDTO {
        private String name;
        private String[] ingredients;

        public CreateCocktailDTO() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getIngredients() {
            return ingredients;
        }

        public void setIngredients(String[] ingredients) {
            this.ingredients = ingredients;
        }

    }

}
