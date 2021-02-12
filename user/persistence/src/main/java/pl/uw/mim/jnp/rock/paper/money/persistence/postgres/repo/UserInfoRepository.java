package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user.UserInfoEntity;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {

  Optional<UserInfoEntity> findByUsername(String username);
}
