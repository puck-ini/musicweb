package org.zchzh.music.VO.adminVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.zchzh.music.VO.SingerTbVO;
import org.zchzh.music.VO.UserTbVO;

public class SingerUserVO {

    @JsonProperty("user")
    private UserTbVO userTbVO;

    @JsonProperty("singer")
    private SingerTbVO singerTbVO;

    public UserTbVO getUserTbVO() {
        return userTbVO;
    }

    public void setUserTbVO(UserTbVO userTbVO) {
        this.userTbVO = userTbVO;
    }

    public SingerTbVO getSingerTbVO() {
        return singerTbVO;
    }

    public void setSingerTbVO(SingerTbVO singerTbVO) {
        this.singerTbVO = singerTbVO;
    }
}
