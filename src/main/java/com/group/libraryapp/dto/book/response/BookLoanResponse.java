package com.group.libraryapp.dto.book.response;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import lombok.Getter;

public class BookLoanResponse {

    @Getter
    private final Long id;

    @Getter
    private final User user;

    @Getter
    private final String bookName;

    private final boolean isReturn;

    public BookLoanResponse(UserLoanHistory userLoanHistory) {
        this.id = userLoanHistory.getId();
        this.user = userLoanHistory.getUser();
        this.bookName = userLoanHistory.getBookName();
        this.isReturn = userLoanHistory.getIsReturn();
    }

    public boolean isReturn() {
        return isReturn;
    }
}
