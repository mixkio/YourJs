package com.project.yourjs.common.dto;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.yourjs.db.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

        @NotNull
        @Size(min = 3, max = 50)
        private String userId;

        @NotNull
        @Size(min = 3, max = 50)
        private String userName;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotNull
        @Size(min = 3, max = 100)
        private String password;

        @NotNull
        @Size(min = 3, max = 50)
        private String nickname;

        private Set<AuthorityDto> authorityDtoSet;

        public static UserDto from(User user) {
                if (user == null)
                        return null;

                return UserDto.builder()
                                .userName(user.getUserName())
                                .nickname(user.getNickname())
                                .authorityDtoSet(user.getAuthorities().stream()
                                                .map(authority -> AuthorityDto.builder()
                                                                .authorityName(authority.getAuthorityName()).build())
                                                .collect(Collectors.toSet()))
                                .build();
        }
}