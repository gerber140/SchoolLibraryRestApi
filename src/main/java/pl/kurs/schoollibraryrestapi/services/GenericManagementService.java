package pl.kurs.schoollibraryrestapi.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schoollibraryrestapi.exceptions.InvalidEntityException;
import pl.kurs.schoollibraryrestapi.exceptions.InvalidIdException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public abstract class GenericManagementService<T extends Identificationable, R extends JpaRepository<T, Long>> implements IManagementService<T> {
    protected R repository;

    @Override
    public T add(T entity) {
        entity = Optional.ofNullable(entity)
                .filter(x -> Objects.isNull(x.getId()))
                .orElseThrow(() -> new InvalidEntityException("Invalid entity for save"));
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(Optional.ofNullable(id)
                .filter(x -> x > 0)
                .orElseThrow(() -> new InvalidIdException("Invalid id: " + id))
        );
    }

    @Override
    public T edit(T entity) {
        entity = Optional.ofNullable(entity)
                .filter(x -> Objects.nonNull(x.getId()))
                .orElseThrow(() -> new InvalidEntityException("Invalid entity for update"));
        return repository.save(entity);
    }

    @Override
    public T get(Long id) {
        return repository.findById(Optional.ofNullable(id)
                .filter(x -> x > 0)
                .orElseThrow(() -> new InvalidIdException("Invalid id:" + id))
        ).orElseThrow(() -> new EntityNotFoundException("Entity with id not found: " + id));
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
