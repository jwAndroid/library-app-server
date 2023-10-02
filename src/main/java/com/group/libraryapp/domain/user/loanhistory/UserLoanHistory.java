package com.group.libraryapp.domain.user.loanhistory;
import com.group.libraryapp.domain.user.User;
import lombok.Getter;
import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Getter
    private String bookName;

    private boolean isReturn;

    @Getter
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

    public boolean getIsReturn() {
        return isReturn;
    }
}
