package com.guoaili.chuxuan.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.guoaili.chuxuan.enumT.RoleType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection ="users")
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    private String username;

    private String password;

    private List<RoleType> roles;

}

