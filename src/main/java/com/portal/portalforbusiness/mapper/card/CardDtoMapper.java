package com.portal.portalforbusiness.mapper.card;

import com.portal.portalforbusiness.mapper.Mapper;
import com.portal.portalforbusiness.models.Card;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.portal.portalforbusiness.dto.CardDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardDtoMapper implements Mapper<CardDto, Card> {
    private static final CardDtoMapper INSTANCE = new CardDtoMapper();

    @Override
    public Card mapFrom(CardDto object) {
        Card card = new Card();

        card.setId(object.getId());
        card.setCcn(object.getCcn());
        card.setCvv(object.getCvv());
        card.setYear(object.getYear());
        card.setMonth(object.getMonth());
        card.setBalance(object.getBalance());
        return card;
    }

    public static CardDtoMapper getInstance() {
        return INSTANCE;
    }
}
