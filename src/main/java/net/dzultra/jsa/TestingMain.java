package net.dzultra.jsa;

import net.dzultra.jsa.bulk_data.BulkData;
import net.dzultra.jsa.bulk_data.BulkDataType;
import net.dzultra.jsa.catalogs.Catalog;
import net.dzultra.jsa.catalogs.CatalogType;
import net.dzultra.jsa.symbology.Symbology;

import java.io.IOException;
import java.util.Arrays;

public class TestingMain {
    public static void main(String[] args) throws IOException {
        ScryfallClient client = new ScryfallClient("JavaScryfallAPITest/1.0");

//        Catalog catalog = new Catalog(client, CatalogType.CARD_NAMES);
//        System.out.println(Arrays.toString(catalog.getCatalog().data()));

//        BulkData bulkData = new BulkData(client);
//        System.out.println(bulkData.getBulkData().data()[0].name());

//        Symbology symbology = new Symbology(client);
//        System.out.println(symbology.getCardSymbols().data()[0]);
//        System.out.println(Arrays.toString(symbology.getManaCost("WUBRG").colors()));
    }
}
