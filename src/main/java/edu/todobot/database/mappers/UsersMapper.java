package edu.todobot.database.mappers;

import edu.todobot.database.dto.UsersDto;
import edu.todobot.database.entities.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    /** @noinspection unused*/
    public UsersDto toDto(UsersEntity usersEntity){
        return UsersDto.builder()
                .id(usersEntity.getId())
                .userName(usersEntity.getUserName())
                .firstName(usersEntity.getFirstName())
                .lastName(usersEntity.getLastName())
                .lang(usersEntity.getLang())
                .goals(usersEntity.getGoals())
                .build();
    }
}
