package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Mistake;
import co.com.crud.requirement.domain.repository.MistakeDomainRepository;
import co.com.crud.requirement.persistence.crud.MistakeCrudRepository;
import co.com.crud.requirement.persistence.entity.MistakeEntity;
import co.com.crud.requirement.persistence.mapper.MistakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MistakesRepository implements MistakeDomainRepository {
   private final MistakeCrudRepository mistakeCrudRepository;

   private final MistakeMapper mistakeMapper;

   @Autowired
   public MistakesRepository(MistakeCrudRepository mistakeCrudRepository, MistakeMapper mistakeMapper) {
       this.mistakeCrudRepository = mistakeCrudRepository;
       this.mistakeMapper = mistakeMapper;
   }
    @Override
    public List<Mistake> getAllMistakes() {
        List<MistakeEntity> mistakeEntities = (List<MistakeEntity>) mistakeCrudRepository.findAll();
        return mistakeMapper.toMistakesEntity(mistakeEntities);
    }

    @Override
    public Optional<Mistake> getMistakeById(Integer id) {
        return mistakeCrudRepository.findById(id).map(mistakeMapper::toMistake);
    }
}
