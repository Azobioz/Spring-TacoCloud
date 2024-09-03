package tacos.data;

import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
