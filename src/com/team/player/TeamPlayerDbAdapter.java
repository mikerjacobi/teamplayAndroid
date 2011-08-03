package com.team.player;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeamPlayerDbAdapter 
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "TeamName";
	public static final String KEY_COACH = "Coach";
	public static final String KEY_SPORT = "Sport";
    

    @SuppressWarnings("unused")
	private static final String TAG = "RecipeDbAdapter";
    
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_CREATE =
        "create table recipes (_id integer primary key autoincrement, "
        + "name text not null);";
    
    private static final String DATABASE_NAME = "teams";
    private static final String DATABASE_TABLE = "teams";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    public static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS recipes");
			onCreate(db);
		}

    }

    public TeamPlayerDbAdapter(Context ctx)
    {
        this.mCtx = ctx;
    }
    
    public TeamPlayerDbAdapter open() throws SQLException
    {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
        
    }

    public void close()
    {
        mDbHelper.close();
    }

    public void delete()
    {
    	//mDbHelper.onUpgrade(mDb, 0, 1);
    	mDb.delete(DATABASE_TABLE, null, null);
    }

    public long createRecipe(String name)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }


    public Cursor fetchAllRecipes()
    {	
    	
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME}, null, null, null, null, null);
    }

    public Cursor fetchRecipe(String name) throws SQLException
    {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_NAME}, KEY_NAME + "=" + name, null, null, null, null, null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        
        return mCursor;

    }
}
