package com.training.jmp.mapper;

import com.training.jmp.entity.User;
import com.training.jmp.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ReferenceMapper {
    private final UserRepository repository;

    public User toUser(Long id) {
        if (id != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public Long toUserId(User input) {
        return Optional.ofNullable(input)
                .map(User::getId)
                .orElse(null);
    }
//    private final EntityManager em;
//    Logger logger = LoggerFactory.getLogger(ReferenceMapper.class);
//
//    public ReferenceMapper(EntityManager em) {
//        this.em = em;
//    }
//
//    @ObjectFactory
//    public <T extends AbstractEntity<?>> T resolve(AbstractDTO<?> sourceDTO,
//                                                   @TargetType Class<T> type) {
//        T entity = null;
//        if (sourceDTO.getId() != null) entity = em.find(type, sourceDTO.getId());
//        try {
//            if (entity == null) {
//                entity = type.getDeclaredConstructor().newInstance();
//            }
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
//                 NoSuchMethodException e) {
//            logger.error(e.getMessage());
//        }
//        return entity;
//    }


}

//@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
//public interface UserMapper extends GenericMapper<User, UserDTO> {
//    @Override
//    @Mapping(target = "id", ignore = false)
//    User asEntity(UserDTO dto);
//}

//@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
//public interface SubscriptionMapper extends GenericMapper<Subscription, SubscriptionDTO> {
//    @Override
//    @Mapping(target = "id", ignore = false)
//    Subscription asEntity(SubscriptionDTO dto);
//}