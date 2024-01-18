package pierpaolo.u5w2d2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;

import java.util.Optional;

@Repository
public interface AutoreDAO extends JpaRepository<Autore, Long> {
    Optional<Autore> findByNome(String nome);
}
