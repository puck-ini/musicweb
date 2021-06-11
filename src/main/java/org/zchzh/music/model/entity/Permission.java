package org.zchzh.music.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpMethod;
import org.zchzh.music.types.PermissionType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Permission extends BaseEntity{

    /**
     * 权限名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 权限类型
     */
    @Enumerated(EnumType.ORDINAL)
    private PermissionType type;
    /**
     * 权限类型为前端页面时表示前端url，权限类型为后端接口时表示后端接口url
     */
    private String url;
    /**
     * 请求方式
     */
    @Enumerated(EnumType.ORDINAL)
    private HttpMethod method;
    /**
     * 父级权限id
     */
    private Long parentId;

}
