package com.group.libraryapp.dto.user.request;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String name;
    private Integer age;
    // int 는 null 을 표현할수 없다.
    // Integer 는 null 을 표현할수 있다.
    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
}
