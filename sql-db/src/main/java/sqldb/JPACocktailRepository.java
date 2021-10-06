package sqldb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPACocktailRepository extends JpaRepository<CocktailEntity, String> {

    @Query(nativeQuery = true, value = "SELECT c.* FROM cocktails c INNER JOIN ingredients i ON c.name = i.ing_name " +
            "WHERE i.name in :name GROUP BY c.name HAVING count(*) >= :matchCount")
    List<CocktailEntity> findByIngredients(@Param("matchCount") Integer matchCount, @Param("name") String... name);

    default List<CocktailEntity> findByIngredients(List<String> name) {
        return findByIngredients(name.size(), name.toArray(new String[0]));
    }


}
