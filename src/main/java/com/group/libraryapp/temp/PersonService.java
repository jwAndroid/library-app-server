package com.group.libraryapp.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(){
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());
        person.setAddress(address);
        // person 이 주인이기 때문에 테이블이 서로 연결이 된다.
//        address.setPerson(person);
        // address 는 주인이 아니기 때문에 테이블이 서로 연결이 안된다.

        // 상대 테이블을 참조하고 (fk) 있으면 연관관계의 주인이 된다.
        // 연관관계의 주인이 아니면 mappedBy (필드명)
        // 위처럼 연관관계의 주인의 세터가 사용되어야만 테이블이 연결된다.

        address.getPerson();
    }
}
