package org.zchzh.music.convert;

import org.zchzh.music.dto.UserDTO;
import org.zchzh.music.entity.newentity.MusicUser;

import java.text.SimpleDateFormat;

/**
 * @author zengchzh
 * @date 2021/6/10
 */
public class UserConvert {

    public UserDTO toDTO(MusicUser user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .loginName(user.getLoginName())
                .gender(user.getGender())
                .mail(user.getMail())
                .address(user.getAddress())
                .birthDate(user.getBirthDate())
                .description(user.getDescription())
                .avatar(user.getAvatar())
                .build();
    }

    public MusicUser toEntity(UserDTO dto) {
        MusicUser musicUser = MusicUser.builder()
                .name(dto.getName())
                .loginName(dto.getLoginName())
                .gender(dto.getGender())
                .mail(dto.getMail())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .description(dto.getDescription())
                .avatar(dto.getAvatar())
                .build();
        musicUser.setId(dto.getId());
        return musicUser;
    }

}
