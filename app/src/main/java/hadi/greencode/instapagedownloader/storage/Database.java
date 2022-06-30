//package hadi.greencode.instapagedownloader.storage;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import io.michaelrocks.paranoid.Obfuscate;
//import ir.greencode.filmy.gson.GsonSingleton;
//import ir.greencode.filmy.models.LastPlay;
//import ir.greencode.filmy.models.StateAnimation;
//import ir.greencode.filmy.models.StateAnime;
//import ir.greencode.filmy.models.StateFilm;
//import ir.greencode.filmy.models.StateSerie;
//import ir.greencode.filmy.network.models.HomePage;
//
//@Obfuscate
//public class Database extends SQLiteOpenHelper {
//
//    private static volatile Database instance;
//
//    private static final String DATABASE_NAME         = "KAK"; // Unknown db name is better!
//    private static final int    VERSION               = 1;   // OldVersion = 3
//    private static final String FILM_STATE_TABLE      = "scaa";
//    private static final String SERIE_STATE_TABLE     = "serie";
//    private static final String ANIMATION_STATE_TABLE = "animation";
//    private static final String ANIME_STATE_TABLE     = "anime";
//
//    private static final String SERIE_LAST_TABLE = "serie_last";
//    private static final String ANIME_LAST_TABLE = "anime_last";
//
//    private static final String LAST_PLAY_TABLE  = "vbgj";
//    private static final String HOME_CACHE_TABLE = "vbgHHHj";
//
//    // Film State
//    private static final String COL_STATE_FILM_ID        = "a";
//    private static final String COL_STATE_TARGET_FILM_ID = "b";
//    private static final String COL_STATE_FILM_POSITION  = "c";
//
//    // Serie State
//    private static final String COL_STATE_SERIE_ID        = "d";
//    private static final String COL_STATE_TARGET_SERIE_ID = "e";
//    private static final String COL_STATE_SERIE_POSITION  = "f";
//
//    // Animation State
//    private static final String COL_STATE_ANIMATION_ID        = "g";
//    private static final String COL_STATE_TARGET_ANIMATION_ID = "k";
//    private static final String COL_STATE_ANIMATION_POSITION  = "l";
//
//    // Anime State
//    private static final String COL_STATE_ANIME_ID        = "m";
//    private static final String COL_STATE_TARGET_ANIME_ID = "n";
//    private static final String COL_STATE_ANIME_POSITION  = "o";
//
//    // Serie Last Video
//    private static final String COL_LAST_SERIE_ID        = "P";
//    private static final String COL_LAST_SERIE_TARGET_ID = "q";
//    private static final String COL_LAST_SERIE_VIDEO_ID  = "r";
//
//    // Anime Last Video
//    private static final String COL_LAST_ANIME_ID        = "s";
//    private static final String COL_LAST_ANIME_TARGET_ID = "t";
//    private static final String COL_LAST_ANIME_VIDEO_ID  = "u";
//
//    // Last Play
//    private static final String COL_lAST_PLAY_ID   = "h";
//    private static final String COL_LAST_PLAY_TYPE = "i";
//    private static final String COL_LAST_PLAY_JSON = "j";
//    public static final  int    FILM_TYPE          = 1;
//    public static final  int    SERIE_TYPE         = 2;
//    public static final  int    ANIME_TYPE         = 3;
//    public static final  int    ANIMATION_TYPE     = 4;
//
//    // Cache
//    private static final String COL_CACHE_HOME_ID   = "K";
//    private static final String COL_CACHE_HOME_JSON = "L";
//
//    private Database(Context context) {
//        super(context.getApplicationContext(), DATABASE_NAME, null, VERSION);
//    }
//
//    public synchronized static Database get(Context context) {
//        if (instance == null) {
//            instance = new Database(context);
//        }
//
//        return instance;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + FILM_STATE_TABLE + "(" +
//                " " + COL_STATE_FILM_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_STATE_TARGET_FILM_ID + " INTEGER, " +
//                " " + COL_STATE_FILM_POSITION + " SIGNED_BIGINT " +
//                ");");
//        db.execSQL("CREATE TABLE " + SERIE_STATE_TABLE + "(" +
//                " " + COL_STATE_SERIE_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_STATE_TARGET_SERIE_ID + " INTEGER, " +
//                " " + COL_STATE_SERIE_POSITION + " SIGNED_BIGINT " +
//                ");");
//        db.execSQL("CREATE TABLE " + ANIMATION_STATE_TABLE + "(" +
//                " " + COL_STATE_ANIMATION_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_STATE_TARGET_ANIMATION_ID + " INTEGER, " +
//                " " + COL_STATE_ANIMATION_POSITION + " SIGNED_BIGINT " +
//                ");");
//        db.execSQL("CREATE TABLE " + ANIME_STATE_TABLE + "(" +
//                " " + COL_STATE_ANIME_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_STATE_TARGET_ANIME_ID + " INTEGER, " +
//                " " + COL_STATE_ANIME_POSITION + " SIGNED_BIGINT " +
//                ");");
//
//        db.execSQL("CREATE TABLE " + SERIE_LAST_TABLE + "(" +
//                " " + COL_LAST_SERIE_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_LAST_SERIE_TARGET_ID + " INTEGER, " +
//                " " + COL_LAST_SERIE_VIDEO_ID + " INTEGER " +
//                ");");
//        db.execSQL("CREATE TABLE " + ANIME_LAST_TABLE + "(" +
//                " " + COL_LAST_ANIME_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_LAST_ANIME_TARGET_ID + " INTEGER, " +
//                " " + COL_LAST_ANIME_VIDEO_ID + " INTEGER " +
//                ");");
//
//        db.execSQL("CREATE TABLE " + LAST_PLAY_TABLE + "(" +
//                " " + COL_lAST_PLAY_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_LAST_PLAY_TYPE + " INTEGER, " +
//                " " + COL_LAST_PLAY_JSON + " TEXT " +
//                ");");
//
//        db.execSQL("CREATE TABLE " + HOME_CACHE_TABLE + "(" +
//                " " + COL_CACHE_HOME_ID + " INTEGER NOT NULL PRIMARY KEY UNIQUE, " +
//                " " + COL_CACHE_HOME_JSON + " TEXT " +
//                ");");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " + FILM_STATE_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + SERIE_STATE_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + ANIMATION_STATE_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + ANIME_STATE_TABLE);
//
//            db.execSQL("DROP TABLE IF EXISTS " + SERIE_LAST_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + ANIME_LAST_TABLE);
//
//            db.execSQL("DROP TABLE IF EXISTS " + LAST_PLAY_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + HOME_CACHE_TABLE);
//            onCreate(db);
//        }
//    }
//
//    @Override
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
////        super.onDowngrade(db, oldVersion, newVersion);    #if you uncomment this line it will throw an exception because of downgrading VERSION!
//    }
//
//    /*************** Film State ****************/
//    public void saveFilmState(StateFilm stateFilm) {
//        if (isStateFilmAvailable(stateFilm.getFilmTargetId())) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + FILM_STATE_TABLE + " SET " + COL_STATE_FILM_POSITION + " = " + stateFilm.getFilmLastPosition() + " WHERE " + COL_STATE_TARGET_FILM_ID + " = " + stateFilm.getFilmTargetId();
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_STATE_TARGET_FILM_ID, stateFilm.getFilmTargetId());
//            contentValues.put(COL_STATE_FILM_POSITION, stateFilm.getFilmLastPosition());
//
//            db.insert(FILM_STATE_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isStateFilmAvailable(int filmTargetID) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + FILM_STATE_TABLE + " WHERE " + COL_STATE_TARGET_FILM_ID + " = " + filmTargetID;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = Integer.parseInt(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public StateFilm getFilmState(int filmTargetId) {
//        StateFilm stateFilm = new StateFilm();
//
//        String         selectQuery = "SELECT * FROM " + FILM_STATE_TABLE + " WHERE " + COL_STATE_TARGET_FILM_ID + " = " + filmTargetId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                stateFilm.setFilmTargetId(cursor.getInt(1));
//                stateFilm.setFilmLastPosition(cursor.getLong(2));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return stateFilm;
//    }
//
//    public void deleteFilmState(int filmTargetId) {
//        if (isStateFilmAvailable(filmTargetId)) {
//            SQLiteDatabase db          = this.getWritableDatabase();
//            String         deleteQuery = "DELETE FROM " + FILM_STATE_TABLE + " WHERE " + COL_STATE_TARGET_FILM_ID + " = " + filmTargetId;
//            db.execSQL(deleteQuery);
//            db.close();
//        }
//    }
//
//    /*************** Serie State ****************/
//    public void saveSerieState(StateSerie stateSerie) {
//        if (isStateSerieAvailable(stateSerie.getSerieTargetId())) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + SERIE_STATE_TABLE + " SET " + COL_STATE_SERIE_POSITION + " = " + stateSerie.getSerieLastPosition() + " WHERE " + COL_STATE_TARGET_SERIE_ID + " = " + stateSerie.getSerieTargetId();
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_STATE_TARGET_SERIE_ID, stateSerie.getSerieTargetId());
//            contentValues.put(COL_STATE_SERIE_POSITION, stateSerie.getSerieLastPosition());
//
//            db.insert(SERIE_STATE_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isStateSerieAvailable(int targetID) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + SERIE_STATE_TABLE + " WHERE " + COL_STATE_TARGET_SERIE_ID + " = " + targetID;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = Integer.parseInt(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public StateSerie getSerieState(int targetId) {
//        StateSerie stateSerie = new StateSerie();
//
//        String         selectQuery = "SELECT * FROM " + SERIE_STATE_TABLE + " WHERE " + COL_STATE_TARGET_SERIE_ID + " = " + targetId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                stateSerie.setSerieTargetId(cursor.getInt(1));
//                stateSerie.setSerieLastPosition(cursor.getLong(2));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return stateSerie;
//    }
//
//    public void deleteSerieState(int targetId) {
//        if (isStateSerieAvailable(targetId)) {
//            SQLiteDatabase db          = this.getWritableDatabase();
//            String         deleteQuery = "DELETE FROM " + SERIE_STATE_TABLE + " WHERE " + COL_STATE_TARGET_SERIE_ID + " = " + targetId;
//            db.execSQL(deleteQuery);
//            db.close();
//        }
//    }
//
//    /*************** Animation State ****************/
//    public void saveAnimationState(StateAnimation stateAnimation) {
//        if (isStateAnimationAvailable(stateAnimation.getAnimationTargetId())) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + ANIMATION_STATE_TABLE + " SET " + COL_STATE_ANIMATION_POSITION + " = " + stateAnimation.getAnimationLastPosition() + " WHERE " + COL_STATE_TARGET_ANIMATION_ID + " = " + stateAnimation.getAnimationTargetId();
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_STATE_TARGET_ANIMATION_ID, stateAnimation.getAnimationTargetId());
//            contentValues.put(COL_STATE_ANIMATION_POSITION, stateAnimation.getAnimationLastPosition());
//
//            db.insert(ANIMATION_STATE_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isStateAnimationAvailable(int targetID) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + ANIMATION_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIMATION_ID + " = " + targetID;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = Integer.parseInt(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public StateAnimation getAnimationState(int targetId) {
//        StateAnimation stateAnimation = new StateAnimation();
//
//        String         selectQuery = "SELECT * FROM " + ANIMATION_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIMATION_ID + " = " + targetId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                stateAnimation.setAnimationTargetId(cursor.getInt(1));
//                stateAnimation.setAnimationLastPosition(cursor.getLong(2));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return stateAnimation;
//    }
//
//    public void deleteAnimationState(int targetId) {
//        if (isStateAnimationAvailable(targetId)) {
//            SQLiteDatabase db          = this.getWritableDatabase();
//            String         deleteQuery = "DELETE FROM " + ANIMATION_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIMATION_ID + " = " + targetId;
//            db.execSQL(deleteQuery);
//            db.close();
//        }
//    }
//
//    /*************** Anime State ****************/
//    public void saveAnimeState(StateAnime stateAnime) {
//        if (isStateAnimeAvailable(stateAnime.getAnimeTargetId())) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + ANIME_STATE_TABLE + " SET " + COL_STATE_ANIME_POSITION + " = " + stateAnime.getAnimeLastPosition() + " WHERE " + COL_STATE_TARGET_ANIME_ID + " = " + stateAnime.getAnimeTargetId();
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_STATE_TARGET_ANIME_ID, stateAnime.getAnimeTargetId());
//            contentValues.put(COL_STATE_ANIME_POSITION, stateAnime.getAnimeLastPosition());
//
//            db.insert(ANIME_STATE_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isStateAnimeAvailable(int targetID) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + ANIME_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIME_ID + " = " + targetID;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = Integer.parseInt(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public StateAnime getAnimeState(int targetId) {
//        StateAnime stateAnime = new StateAnime();
//
//        String         selectQuery = "SELECT * FROM " + ANIME_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIME_ID + " = " + targetId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                stateAnime.setAnimeTargetId(cursor.getInt(1));
//                stateAnime.setAnimeLastPosition(cursor.getLong(2));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return stateAnime;
//    }
//
//    public void deleteAnimeState(int targetId) {
//        if (isStateAnimeAvailable(targetId)) {
//            SQLiteDatabase db          = this.getWritableDatabase();
//            String         deleteQuery = "DELETE FROM " + ANIME_STATE_TABLE + " WHERE " + COL_STATE_TARGET_ANIME_ID + " = " + targetId;
//            db.execSQL(deleteQuery);
//            db.close();
//        }
//    }
//
//    /*************** Serie Last Played ****************/
//    public void addSerieLastPlayed(int serieId, int videoId) {
//        if (isSerieLastPlayedAvailable(serieId)) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + SERIE_LAST_TABLE + " SET " + COL_LAST_SERIE_VIDEO_ID + " = " + videoId + " WHERE " + COL_LAST_SERIE_TARGET_ID + " = " + serieId;
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_LAST_SERIE_TARGET_ID, serieId);
//            contentValues.put(COL_LAST_SERIE_VIDEO_ID, videoId);
//
//            db.insert(SERIE_LAST_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isSerieLastPlayedAvailable(int serieId) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + SERIE_LAST_TABLE + " WHERE " + COL_LAST_SERIE_TARGET_ID + " = " + serieId;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = cursor.getInt(1);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public int getSerieLastPlayedID(int serieId) {
//        int lastId = 0;
//
//        String         selectQuery = "SELECT * FROM " + SERIE_LAST_TABLE + " WHERE " + COL_LAST_SERIE_TARGET_ID + " = " + serieId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                lastId = cursor.getInt(2);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return lastId;
//    }
//
//    /*************** Serie Last Played ****************/
//    public void addAnimeLastPlayed(int animeId, int videoId) {
//        if (isAnimeLastPlayedAvailable(animeId)) {
//            SQLiteDatabase db    = this.getWritableDatabase();
//            String         query = "UPDATE " + ANIME_LAST_TABLE + " SET " + COL_LAST_ANIME_VIDEO_ID + " = " + videoId + " WHERE " + COL_LAST_ANIME_TARGET_ID + " = " + animeId;
//            db.execSQL(query);
//            db.close();
//
//        } else {
//            SQLiteDatabase db            = this.getWritableDatabase();
//            ContentValues  contentValues = new ContentValues();
//
//            contentValues.put(COL_LAST_ANIME_TARGET_ID, animeId);
//            contentValues.put(COL_LAST_ANIME_VIDEO_ID, videoId);
//
//            db.insert(ANIME_LAST_TABLE, null, contentValues);
//            db.close();
//        }
//    }
//
//    private boolean isAnimeLastPlayedAvailable(int animeId) {
//        int            finded      = 0;
//        String         selectQuery = "SELECT * FROM " + ANIME_LAST_TABLE + " WHERE " + COL_LAST_ANIME_TARGET_ID + " = " + animeId;
//        SQLiteDatabase db          = this.getWritableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                finded = cursor.getInt(1);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return finded != 0;
//    }
//
//    public int getAnimeLastPlayedID(int animeId) {
//        int lastId = 0;
//
//        String         selectQuery = "SELECT * FROM " + ANIME_LAST_TABLE + " WHERE " + COL_LAST_ANIME_TARGET_ID + " = " + animeId;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                lastId = cursor.getInt(2);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return lastId;
//    }
//
//    /*************** Last Play ****************/
//    public void addLastPlay(int type, Object jsonObject) {
//        resetLastPlay();
//        String toJson = GsonSingleton.getInstance().getGson().toJson(jsonObject);
//
//        SQLiteDatabase db            = this.getWritableDatabase();
//        ContentValues  contentValues = new ContentValues();
//
//        contentValues.put(COL_LAST_PLAY_TYPE, type);
//        contentValues.put(COL_LAST_PLAY_JSON, toJson);
//
//        db.insert(LAST_PLAY_TABLE, null, contentValues);
//        db.close();
//    }
//
//    public void resetLastPlay() {
//        SQLiteDatabase db          = this.getWritableDatabase();
//        String         deleteQuery = "DELETE FROM " + LAST_PLAY_TABLE;
//        db.execSQL(deleteQuery);
//        db.close();
//    }
//
//    public LastPlay getLastPlay() {
//        LastPlay stateFilm = new LastPlay();
//
//        String         selectQuery = "SELECT * FROM " + LAST_PLAY_TABLE;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                stateFilm.setType(cursor.getInt(1));
//                stateFilm.setJson(cursor.getString(2));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return stateFilm;
//    }
//
//    /*************** Cache ****************/
//    public void saveHomePageCache(HomePage homePage) {
//        resetHomePageCache();
//        String toJson = GsonSingleton.getInstance().getGson().toJson(homePage);
//
//        SQLiteDatabase db            = this.getWritableDatabase();
//        ContentValues  contentValues = new ContentValues();
//
//        contentValues.put(COL_CACHE_HOME_JSON, toJson);
//
//        db.insert(HOME_CACHE_TABLE, null, contentValues);
//        db.close();
//    }
//
//    public void resetHomePageCache() {
//        SQLiteDatabase db          = this.getWritableDatabase();
//        String         deleteQuery = "DELETE FROM " + HOME_CACHE_TABLE;
//        db.execSQL(deleteQuery);
//        db.close();
//    }
//
//    public HomePage getHomePageCache() {
//        HomePage homePage = new HomePage();
//
//        String         selectQuery = "SELECT * FROM " + HOME_CACHE_TABLE;
//        SQLiteDatabase db          = getReadableDatabase();
//        Cursor         cursor      = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                homePage = GsonSingleton.getInstance().getGson().fromJson(cursor.getString(1), HomePage.class);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return homePage;
//    }
//
//}
