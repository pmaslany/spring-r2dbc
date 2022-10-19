package acquiry.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SomeRepository extends ReactiveCrudRepository<TestEntity, Long> {
}