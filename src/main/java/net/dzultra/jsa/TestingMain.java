package net.dzultra.jsa;

import net.dzultra.jsa.cards.Card;
import net.dzultra.jsa.cards.CardNameList;
import net.dzultra.jsa.cards.CardRequester;
import net.dzultra.jsa.cards.enums.CardIDType;
import net.dzultra.jsa.cards.enums.Language;
import net.dzultra.jsa.catalogs.Catalog;
import net.dzultra.jsa.catalogs.CatalogType;
import net.dzultra.jsa.sets.MTGSet;
import net.dzultra.jsa.sets.MTGSetList;
import net.dzultra.jsa.sets.SetRequester;
import net.dzultra.jsa.sets.SetType;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.UUID;

public class TestingMain {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        ScryfallClient client = new ScryfallClient("JavaScryfallAPITest/1.0.2");

//        Catalog catalog = new Catalog(client, CatalogType.ARTIST_NAMES);
//        for (String artist : catalog.getCatalog().data()) {
//            System.out.println(artist);
//        }

//        BulkData bulkData = new BulkData(client);
//        System.out.println(bulkData.getBulkData().data()[0].name());

//        Symbology symbology = new Symbology(client);
//        System.out.println(symbology.getCardSymbols().data()[0]);
//        System.out.println(Arrays.toString(symbology.getManaCost("WUBRG").colors()));

//        Card[] cards = new CardRequester(client).getCardsByQuery("Chandra", null, null, SortMode.DESCEND).cards();
//        for (Card card : cards) {
//            if (card.loyalty() == null) return;
//            System.out.println(card.name());
//        }

//        Card card = new CardRequester(client).getCardByName("Demonic Tutor", "CMM", true);
//        System.out.println(card.name());
//        System.out.println(card.image_uris());
//        System.out.println(card.prices());

//        Catalog catalog = new Catalog(client, CatalogType.TOUGHNESSES);
//        System.out.println(Arrays.toString(catalog.getCatalog().data()));

//        MTGSetList setList = new SetRequester(client).getAllSets();
//        System.out.println(setList.sets()[100].code());
//        System.out.println(setList.sets()[100].name());

//        MTGSet set = new SetRequester(client).getSetByUUID(UUID.fromString("2ec77b94-6d47-4891-a480-5d0b4e5c9372"));
//        System.out.println(set.code());
//        System.out.println(set.name());
//        System.out.println(SetType.fromString(set.set_type()));

//        CardNameList cardNameList = new CardRequester(client).getCardNamesByQuery("Chandra", true);
//        for (String name : cardNameList.card_names()) {
//            System.out.println(name);
//        }

//        Card card = new CardRequester(client).getRandomCard();
//        System.out.println(card.name());
//        Desktop.getDesktop().browse(card.image_uris().large());

//        Card card = new CardRequester(client).getCardBySetAndNumber("BLB", "63", Language.EN);
//        System.out.println(card.name());
//        Desktop.getDesktop().browse(card.image_uris().large());

//        Card card = new CardRequester(client).getCardById("59697", CardIDType.MTGO);
//        System.out.println(card.name());
//        Desktop.getDesktop().browse(card.image_uris().large());
    }
}
