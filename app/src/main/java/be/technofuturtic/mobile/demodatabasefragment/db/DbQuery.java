package be.technofuturtic.mobile.demodatabasefragment.db;

public class DbQuery {

    public final static String DB_NAME = "recipe_db";
    public final static int DB_VERSION = 1;

    /**
     * Table Recipe
     */
    public static class Recipe {
        // Table name
        public static final String TABLE_NAME = "Recipe";

        // Column names
        public static final String COL_ID = "Recipe_ID";
        public static final String COL_NAME = "Name";
        public static final String COL_RATING = "Rating";

        // Query (Create / Drop)
        public static final String QUERY_CREATE =
                "CREATE TABLE " + TABLE_NAME + " ( "
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NAME + " VARCHAR(50) NOT NULL, "
                    + COL_RATING + " INTEGER "
                + ");" ;

        public static final String QUERY_DROP =
                "DROP TABLE " + TABLE_NAME + ";" ;
    }

    /**
     * Table Ingredient
     */
    public static class Ingredient {
        // Table name
        public static final String TABLE_NAME = "Ingredient";

        // Column names
        public static final String COL_ID = "Ingredient_ID";
        public static final String COL_NAME = "Name";
        public static final String COL_DESC = "Description";

        // Query (Create / Drop)
        public static final String QUERY_CREATE =
                "CREATE TABLE " + TABLE_NAME + " ( "
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NAME + " VARCHAR(50) NOT NULL, "
                    + COL_DESC + " VARCHAR(255) "
                + ");" ;

        public static final String QUERY_DROP =
                "DROP TABLE " + TABLE_NAME + ";" ;
    }

    /**
     * Table RecipeIngredient
     */
    public static class RecipeIngredient {
        // Table name
        public static final String TABLE_NAME = "Recipe_Ingredient";

        // Column names
        public static final String COL_ID = "Recipe_Ingredient_ID";
        public static final String COL_RECIPE_ID = "Recipe_ID";
        public static final String COL_INGREDIENT_ID = "Ingredient_ID";
        public static final String COL_QUANTITY = "Quantity";

        // Query (Create / Drop)
        public static final String QUERY_CREATE =
                "CREATE TABLE " + TABLE_NAME + " ( "
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_RECIPE_ID + " INTEGER NOT NULL, "
                    + COL_INGREDIENT_ID + " INTEGER NOT NULL, "
                    + COL_QUANTITY + " VARCHAR(255), "
                    + "CONSTRAINT UK_Recipe_Ingredient UNIQUE(" + COL_RECIPE_ID + ", " +  COL_INGREDIENT_ID + ")"
                + ");" ;

        public static final String QUERY_DROP =
                "DROP TABLE " + TABLE_NAME + ";" ;
    }
}
