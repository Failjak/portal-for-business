package com.portal.portalforbusiness.mapper.card;

import com.portal.portalforbusiness.dto.CardDto;
import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Card;


public class CardMapper implements Mapper<Card, CardDto> {
    private static final CardMapper INSTANCE = new CardMapper();

    @Override
    public CardDto mapFrom(Card object) {
        return CardDto.builder().
                id(object.getId()).
                ccn(object.getCcn()).
                cvv(object.getCvv()).
                month(object.getMonth()).
                year(object.getYear()).
                balance(object.getBalance()).
                build();
    }

    public static CardMapper getInstance() {
        return INSTANCE;
    }
}
