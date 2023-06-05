package com.group.libraryapp.domain.user.loanhistory;
import com.group.libraryapp.domain.user.User;
import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String bookName;

    private boolean isReturn;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    protected UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean getIsReturn() {
        return isReturn;
    }
}
