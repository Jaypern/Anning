package com.springboot.springbootlogindemo.repository;
import com.springboot.springbootlogindemo.domain.Caller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CallerDao extends JpaRepository<Caller, Long> {
    Caller findByUname(String uname);
    Caller findByUnameAndText(String uname,String text);
}
