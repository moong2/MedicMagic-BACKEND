package MedicMagic.user.dao;

import MedicMagic.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao{
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
    int getCountEachId(String id);
    void update(User user);
}
