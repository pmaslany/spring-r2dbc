package acquiry.repo;

import org.springframework.data.annotation.Id;

public class TestEntity {
    @Id
    private Long id;
    private final String name;
    public TestEntity(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}