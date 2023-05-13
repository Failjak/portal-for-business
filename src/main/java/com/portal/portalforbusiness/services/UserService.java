package com.portal.portalforbusiness.services;

import com.portal.portalforbusiness.dao.user.UserDao;
import com.portal.portalforbusiness.dao.user.UserDaoImpl;
import com.portal.portalforbusiness.dto.CardDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.mapper.card.CardDtoMapper;
import com.portal.portalforbusiness.mapper.user.UserDtoMapper;
import com.portal.portalforbusiness.mapper.user.UserMapper;
import com.portal.portalforbusiness.models.RoleType;
import com.portal.portalforbusiness.models.User;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements Service {
    private static final UserService INSTANCE = new UserService();

    private final UserDao usersDao = new UserDaoImpl();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();
    private final CardDtoMapper cardDtoMapper = CardDtoMapper.getInstance();
    private final ProductService productService = ProductService.getInstance();

    public Optional<UserDto> login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()){
            return Optional.empty();
        }
        return usersDao.findByUsernameAndPassword(username, password).
                map(userMapper::mapFrom);
    }

    public Optional<UserDto> setCard(UserDto userDto, CardDto cardDto) {
        return usersDao.setCard(userDto.getId(), cardDtoMapper.mapFrom(cardDto) , RoleType.BUYER).map(userMapper::mapFrom);
    }

    public Optional<UserDto> findUser(UserDto userDto) {
        List<Integer> boughtProductIds = productService.getBoughtProductIdsByUser(userDto);
        return usersDao.findById(userDto.getId()).
                map(user -> userMapper.mapFrom(user, boughtProductIds));
    }

    public Optional<List<UserDto>> getAllUsers(Integer limit) {
        Optional<List<User>> users = usersDao.getAllUsers(limit);
        if (users.isEmpty())
            return Optional.empty();
        return Optional.of(users.get().
                stream().
                map(userMapper::mapFrom).
                collect(Collectors.toList()));
    }

    public void saveUser(UserDto userDto) {
        User user = userDtoMapper.mapFrom(userDto);
        usersDao.save(user);
    }

    public Optional<UserDto> blockUser(UserDto userDto) {
        return usersDao.block(userDto.getId()).
                map(userMapper::mapFrom);
    }

    public Optional<UserDto> deleteUser(UserDto userDto) {
        return usersDao.delete(userDto.getId()).
                map(userMapper::mapFrom);
    }

    public Optional<UserDto> updateUser(UserDto userDto, UserDto newUserDto) {
        return usersDao.update(userDtoMapper.mapFrom(userDto), userDtoMapper.mapFrom(newUserDto)).map(userMapper::mapFrom);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
