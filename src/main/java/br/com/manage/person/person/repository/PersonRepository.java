package br.com.manage.person.person.repository;

import br.com.manage.person.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByCpf(String person);
}
