package com.maciejmalak.daogen;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    private final static int DB_VERSION = 1;

    public static void main(String... args) throws Exception {
        final Schema schema = new Schema(DB_VERSION, "com.maciejmalak.whatcanieat.model");
        generateFoodEntity(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "../app/src/main/java");
    }

    private static void generateFoodEntity(final Schema schema) {
        final Entity productTranslations = schema.addEntity("ProductTranslations");

        final Entity product = schema.addEntity("Product");
        product.addIdProperty().primaryKey().unique().notNull().autoincrement();

        /* 1:N from product to productTrans*/
        final Property productId = productTranslations.addLongProperty("productId").primaryKey().notNull().getProperty();
        product.addToMany(productTranslations, productId);

        final Entity language = schema.addEntity("Language");
        language.addStringProperty("languageCode").notNull().unique().primaryKey();
        language.addStringProperty("languageName").notNull().unique();

        /* 1:N from lang to productTrans*/
        final Property languageId = productTranslations.addStringProperty("languageCode").primaryKey().notNull().getProperty();
        language.addToMany(productTranslations, languageId);

        /*rest of product translation columns */
        productTranslations.addStringProperty("productTranslation").notNull();
        productTranslations.addStringProperty("categoryTranslation").notNull();
        productTranslations.addStringProperty("restrictionTranslation").notNull();
    }
}
