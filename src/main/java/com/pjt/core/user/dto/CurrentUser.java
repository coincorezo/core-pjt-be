package com.pjt.core.user.dto;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.exception.UserException;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentUser {

    private String id;

    private String email;

    private String useYn;

    private String userLevel;

    @Builder
    private CurrentUser(String id, String email, String useYn, String userLevel) {
        this.id = id;
        this.email = email;
        this.useYn = useYn;
        this.userLevel = userLevel;
    }

    /**
     * 현재 사용자의 ID와 매개변수로 받은 ID가 같은지 확인
     *
     * @param id 사용자 ID
     * @return 같으면 true, 다르면 false
     */
    public boolean isEqualsId(String id) {
        return this.id.equals(id);
    }

    /**
     * 현재 사용자의 ID와 매개변수로 받은 ID가 같은지 확인 틀리면 exception
     *
     * @param id 사용자 ID
     */
    /**
     * <pre>
     * 현재 사용자의 ID와 매개변수로 받은 ID가 같은지 확인 틀리면 exception
     * </pre>
     *
     * @param : id 사용자 ID
     * @return :
     * @throws :
     * @author : JangDongHoo
     * @date : 2024-05-24
     */
    public void validUserId(String id) {
        if (!this.id.equals(id)) {
            throw new UserException(ErrorCode.NOT_EQUALS_USER);
        }
    }

    public static CurrentUser fromEntity(User user) {
        return CurrentUser.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .useYn(user.getUseYn())
                .userLevel(user.getUserLevel())
                .build();
    }

}
