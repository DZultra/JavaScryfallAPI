package net.dzultra.jsa;

import net.dzultra.jsa.cards.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestingMain {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        ScryfallClient client = new ScryfallClient("JavaScryfallAPITest/1.0");

//        Catalog catalog = new Catalog(client, CatalogType.CARD_NAMES);
//        System.out.println(Arrays.toString(catalog.getCatalog().data()));

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

        Card card = new CardRequester(client).getCardByName("Tempel der Eingebung", true);
        System.out.println(card.image_uris().large());
    }
}
