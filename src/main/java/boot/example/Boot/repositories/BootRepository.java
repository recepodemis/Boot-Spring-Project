package boot.example.Boot.repositories;
import java.util.List;

import boot.example.Boot.entities.Boot;
import boot.example.Boot.enums.BootType;
import org.springframework.data.repository.CrudRepository;
import boot.example.Boot.entities;
public interface BootRepository extends CrudRepository<Boot,Integer>{
    List<Boot> findBySize(Float Size);
    List<Boot> findByMaterial(String material);
    List<Boot> findByType(BootType type);
    List<Boot> findByQuantityMoreThan(Integer quantity);

}