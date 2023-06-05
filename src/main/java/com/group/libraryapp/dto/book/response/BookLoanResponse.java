package com.group.libraryapp.dto.book.response;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import java.util.List;

public class BookLoanResponse {
    private final Long id;
    private final User user;
    private final String bookName;
    private final boolean isReturn;

    public BookLoanResponse(Long id, User user, String bookName, boolean isReturn) {
        this.id = id;
        this.user = user;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }

    public BookLoanResponse(UserLoanHistory userLoanHistory) {
        this.id = userLoanHistory.getId();
        this.user = userLoanHistory.getUser();
        this.bookName = userLoanHistory.getBookName();
        this.isReturn = userLoanHistory.getIsReturn();
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
    public boolean isReturn() {
        return isReturn;
    }
}
