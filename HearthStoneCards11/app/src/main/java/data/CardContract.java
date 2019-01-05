package data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CardContract {

    public static final String CONTENT_AUTHORITY = "com.example.m.hearthstonecards";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CARD = "card";
    public static final String PATH_INFO="CardInfoEntry";


    public static final class CardEntry implements BaseColumns {
        public static final String TABLE_NAME               = "card";
        public static final String COLUMN_ID                = "id";
        public static final String COLUMN_InfoID            = "info_id";
        public static final String COLUMN_TYPE              = "type";
        public static final String COLUMN_RARITY            = "rarity";
        public static final String COLUMN_NAME              = "name";
        public static final String COLUMN_CARD_CLASS        = "card_class";
        public static final String COLUMN_IMG_URL           = "img_url";


        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_CARD)
                .build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_CARD;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_CARD;

        public static Uri buildCardUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        /*
        public static Uri buildWeatherLocationWithDate(String locationSetting, long date)
        {
            return CONTENT_URI.buildUpon()
                    .appendPath(locationSetting)
                    .appendPath(Long.toString(date))
                    .build();
        }
        public static Uri buildWeatherLocationWithStartDate(String locationSetting, long startDate)
        {
            return CONTENT_URI.buildUpon()
                    .appendPath(locationSetting)
                    .appendQueryParameter(COLUMN_DATE, Long.toString(startDate))
                    .build();
        }
        public static String getLocationSettingFromUri(Uri uri)
        {
            return uri.getPathSegments().get(1);
        }
        public static long getDateFromUri(Uri uri)
        {
            return Long.parseLong(uri.getPathSegments().get(2));
        }
        public static long getStartDateFromUri(Uri uri)
        {
            String dateString = uri.getQueryParameter(COLUMN_DATE);
            if (null != dateString && dateString.length() > 0)
                return Long.parseLong(dateString);
            else
                return 0;
        }*/

    }
    public static final class CardInfoEntry implements BaseColumns{
        public static final String TABLE_NAME               = "CardInfoEntry";
        public static final String COLUMN_ID                = "id";
        public static final String COLUMN_TEXT              = "text";
        public static final String COLUMN_COST              = "cost";
        public static final String COLUMN_HEALTH            = "health";
        public static final String COLUMN_ATTACK            = "attack";
        public static final String COLUMN_DURABILITY        = "durability";
        public static final String COLUMN_ARMOR             = "armor";
        public static final String COLUMN_IS_COLLECTIBLE    = "is_collectible";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INFO)
                .build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_INFO;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_INFO;

        public static Uri buildCardInfoUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        // Method for building a Location and Date URI (Type 3)
        public static Uri buildWeatherLocationWithDate(String locationSetting, long date) {
            return CONTENT_URI.buildUpon()
                    .appendPath(locationSetting)
                    .appendPath(Long.toString(date))
                    .build();
        }


    }

}
