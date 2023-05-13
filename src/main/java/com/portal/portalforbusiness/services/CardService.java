package com.portal.portalforbusiness.services;


import com.portal.portalforbusiness.dao.user.CardDaoImpl;
import com.portal.portalforbusiness.dto.CardDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.mapper.card.CardDtoMapper;
import com.portal.portalforbusiness.mapper.card.CardMapper;

import java.util.Optional;

public class CardService implements Service {
    private static final CardService INSTANCE = new CardService();

    private final CardDtoMapper cardDtoMapper = CardDtoMapper.getInstance();
    private final CardMapper cardMapper = CardMapper.getInstance();
    private final UserService userService = UserService.getInstance();

    private final CardDaoImpl cardDao = new CardDaoImpl();


    public Optional<UserDto> saveCard(CardDto cardDto, UserDto userDto) {
        Optional<CardDto> cardDtoRes = cardDao.save(cardDtoMapper.mapFrom(cardDto)).map(cardMapper::mapFrom);
        if (cardDtoRes.isPresent()) {
            if (userDto.getCard() != null) {
                cardDao.delete(userDto.getCard().getId());
            }
            return userService.setCard(userDto, cardDtoRes.get());
        }
        return Optional.empty();
    }


    public static CardService getInstance() {
        return INSTANCE;
    }
}
