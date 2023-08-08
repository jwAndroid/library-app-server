package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
* User 와 UserLoanHistory 는 서로 OneToMany manyToOne 관계이다.
*
* */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false, length = 20, name = "name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다" , name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void removeOneHistory() {
        userLoanHistories.removeIf(history -> "책1".equals(history.getBookName()));
    }

    public void loanBook(String bookName) {
        userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
    // this.userLoanHistories 두개가 서로 협력관계 이기때문에 해당하는 리스트가 존재한다.
    // 테이블에 있는 이름과 같은 객체를 찾아낸다.
    // 찾은 객체를 반납처리(isReturn 을 true)한다.
    // isReturn 을 트루로 바꾸게되면 트랙잭션의 영속성 컨텍스트로 인해 save 가 자동으로 된다.
    // stream 은 함수형 프로그래밍을 사용할수 있게 해주는 함수이다.
    // 이런걸 도메인계층의 비니지스로직 이라고 표현한다. (서비스계층에서 -> 도메인계층으로 로직이 이동)
}
