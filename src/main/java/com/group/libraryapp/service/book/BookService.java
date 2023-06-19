package com.group.libraryapp.service.book;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.dto.book.response.BookLoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    // 스프링 빈을 통한 의존성 주입
//    public BookService(BookRepository bookRepository,
//                       UserLoanHistoryRepository userLoanHistoryRepository ,
//                       UserRepository userRepository
//    ) {
//        this.bookRepository = bookRepository;
//        this.userLoanHistoryRepository = userLoanHistoryRepository;
//        this.userRepository = userRepository;
//    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책정보 가져옴
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록 정보를 확인해서 대출중인지 확인.
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            // 3. 만약에 확인했는데 대출중이라면 예외를 발생시킴.
            throw new IllegalArgumentException("대출중인 책입니다.");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        user.loanBook(request.getBookName());
        // 캐스케이드 옵션에 의해서 가능한일이다.

        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
//        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        // dto 에서 사용자의 이름을 받아 user 찾아주기.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
        // User 의 협력관계로 인한 도메인계층의 비니지스로직

        // 테이블에서 찾은 user 를 사용해서 유저아이디, dto 의 책이름으로 UserLoanHistory
        // 테이블에서 해당하는 정보를 찾아낸다.
//        UserLoanHistory userLoanHistory = userLoanHistoryRepository
//                .findByUserIdAndBookName(user.getId(), request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);

        // 정보를 찾았다면 isReturn 을 트루로 바꾸고, 다시 저장해준다.
//        userLoanHistory.doReturn();
//        userLoanHistoryRepository.save(userLoanHistory); 객체가 변경되면 save 는 생략 가능하다.
    }

    @Transactional(readOnly = true)
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book book(long id) {
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<BookLoanResponse> loans() {
        return userLoanHistoryRepository.findAll()
                .stream()
                .map(BookLoanResponse::new)
                .collect(Collectors.toList());
    }
}
