package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 角色实体类
 */
public class Role {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合java
     */
    private Set<Permissions> permissions;
}
