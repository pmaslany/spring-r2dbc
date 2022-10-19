package acquiry;

import acquiry.repo.SomeRepository;
import acquiry.repo.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
@Configuration
@EnableR2dbcRepositories
public class AcquiryApplication {

    public static void main(String[]args) {
        SpringApplication.run(AcquiryApplication.class, args);
    }

    @RestController
    public static class SomeController {

        @Autowired
        private SomeService service;

        @GetMapping(value = "transactional")
        public Mono<Long> transactional() {
            return service.fetchAndStoreTransactional();
        }

        @GetMapping(value = "nontransactional")
        public Mono<Long> nontransactional() {
            return service.fetchAndStoreNonTransactional();
        }
    }

    @Service
    public static class SomeService {

        @Autowired
        private SlowServiceClient slowServiceClient;

        @Autowired
        private SomeRepository repository;

        @Transactional
        public Mono<Long> fetchAndStoreTransactional() {
            return process();
        }

        public Mono<Long> fetchAndStoreNonTransactional() {
            return process();
        }

        private Mono<Long> process() {
            return slowServiceClient.fetchData()
                    .flatMap(data -> repository.save(new TestEntity(data)))
                    .flatMap(entity -> repository.count());
        }

    }

    @Component
    public static class SlowServiceClient {
        public Mono<String> fetchData() {
            return Mono.delay(Duration.ofSeconds(3)).then(Mono.just("res"));
        }
    }
}