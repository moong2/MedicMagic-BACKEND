package MedicMagic.user.service;

import MedicMagic.user.dao.UserDao;
import MedicMagic.user.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}