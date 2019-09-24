package DatabaseProject.Repositories;

import DatabaseProject.Models.PlantModel;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<PlantModel, String>
{
}
