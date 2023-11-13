package boot.example.Boot.controllers;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Optional;

import boot.example.Boot.entities.Boot;
import boot.example.Boot.enums.BootType;
import boot.example.Boot.repositories.BootRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/boots")
public class BootController {
    private BootRepository bootRepository;
    public BootController(BootRepository bootRepository){
        this.bootRepository = bootRepository;
    }
    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        return this.bootRepository.findAll();
    }

    @GetMapping("/types")
    public List<BootType> getBootTypes() {
        return Arrays.asList(BootType.values());
    }

    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        Boot newBoot = this.bootRepository.findAll();
        newBoot.save(boot);
        return newBoot;
    }

    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {
        Optional<Boot> bootToDeleteOptional = this.bootRepository.findById(id);
        if(!bootToDeleteOptional.isPresent()){
            return null;
        }
        Boot bootToDelete = bootToDeleteOptional.get();
        this.bootRepository.delete(bootToDelete);
        return bootToDelete;
    }

    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootToIncrementOptional = this.bootRepository.findById(id);
        if(!bootToIncrementOptional.isPresent()){
            return null;
        }
        Boot bootToIncrement = bootToIncrementOptional.get();
        bootToIncrement.setIncrement(bootToIncrement.getIncrement() + 1);
        this.bootRepository.save(bootToIncrement);
        return bootToIncrement;
    }

    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootToDecrementOptional = this.bootRepository.findById(id);
        if(!bootToDecrementOptional.isPresent()){
            return null;
        }
        Boot bootToDecrement = bootToDecrementOptional.get();
        bootToDecrement.setDecrement(bootToDecrement.getDecrement() - 1);
        this.bootRepository.save(bootToDecrement);
        return bootToDecrement;
    }

    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) throws QueryNotSupportedException {
        if (Objects.nonNull(material)) {
            if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, type, size, and minimum
                // quantity
                findBySize(size);
                findByMaterial(material);
                findByType(type);
                findByQuantityMoreThan(minQuantity);

            } else if (Objects.nonNull(type) && Objects.nonNull(size)) {
                // call the repository method that accepts a material, size, and type
                findBySize(size);
                findByMaterial(material);
                findByType(type);

            } else if (Objects.nonNull(type) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, a type, and a minimum
                // quantity
                findByMaterial(material);
                findByType(type);
                findByQuantityMoreThan(minQuantity);

            } else if (Objects.nonNull(type)) {
                // call the repository method that accepts a material and a type
                findByMaterial(material);
                findByType(type);
            } else {
                // call the repository method that accepts only a material
                findByMaterial(material);
            }
        } else if (Objects.nonNull(type)) {
            if (Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type, size, and minimum quantity
                findBySize(size);
                findByType(type);
                findByQuantityMoreThan(minQuantity);

            } else if (Objects.nonNull(size)) {
                // call the repository method that accepts a type and a size
                findBySize(size);
                findByType(type);
            } else if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type and a minimum quantity
                findByType(type);
                findByQuantityMoreThan(minQuantity);
            } else {
                // call the repository method that accept only a type
                findByType(type);
            }
        } else if (Objects.nonNull(size)) {
            if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a size and a minimum quantity
                findBySize(size);
                findByQuantityMoreThan(minQuantity);
            } else {
                // call the repository method that accepts only a size
                findBySize(size);
            }
        } else if (Objects.nonNull(minQuantity)) {
            // call the repository method that accepts only a minimum quantity
            findByQuantityMoreThan(minQuantity);
        } else {
            throw new QueryNotSupportedException("This query is not supported! Try a different combination of search parameters.");
        }
    }

}