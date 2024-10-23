package com.rehan.laundryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.rehan.laundryapp.model.ModelPelanggan;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Nama dan versi database
    public static final String DATABASE_NAME = "LaundryDB";
    public static final int DATABASE_VERSION = 11;

    // Nama tabel dan kolom
    public static final String TABLE_PELANGGAN = "pelanggan";
    public static final String KEY_PELANGGAN_ID = "pelanggan_id";
    public static final String KEY_PELANGGAN_NAMA = "nama";
    public static final String KEY_PELANGGAN_EMAIL = "email";
    public static final String KEY_PELANGGAN_HP = "hp";

    // Query untuk membuat tabel Pelanggan
    private static final String CREATE_TABLE_PELANGGAN =
            "CREATE TABLE " + TABLE_PELANGGAN + "("
                    + KEY_PELANGGAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_PELANGGAN_NAMA + " TEXT, "
                    + KEY_PELANGGAN_EMAIL + " TEXT, "
                    + KEY_PELANGGAN_HP + " TEXT" + ")";

    // Konstruktor
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel Pelanggan
        db.execSQL(CREATE_TABLE_PELANGGAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Jika versi database berubah, tabel lama akan dihapus dan dibuat kembali
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PELANGGAN);
        onCreate(db);
    }

    // Fungsi untuk menambahkan pelanggan
    public boolean insertPelanggan(ModelPelanggan mp) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PELANGGAN_NAMA, mp.getNama());
        contentValues.put(KEY_PELANGGAN_EMAIL, mp.getEmail());
        contentValues.put(KEY_PELANGGAN_HP, mp.getHp());

        long id = database.insert(TABLE_PELANGGAN, null, contentValues);
        database.close();

        return id != -1; // Jika id tidak -1, berarti insert berhasil
    }

    // Fungsi untuk mengambil daftar pelanggan
    public List<ModelPelanggan> getPelanggan() {
        List<ModelPelanggan> pel = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PELANGGAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ModelPelanggan k = new ModelPelanggan();
                k.setId(cursor.getString(0)); // Mengambil id pelanggan
                k.setNama(cursor.getString(1)); // Mengambil nama pelanggan
                k.setEmail(cursor.getString(2)); // Mengambil email pelanggan
                k.setHp(cursor.getString(3)); // Mengambil nomor hp pelanggan

                pel.add(k);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pel;
    }

    // Fungsi untuk memperbarui pelanggan
    public boolean updatePelanggan(String id, String name, String email, String hp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PELANGGAN_NAMA, name);
        contentValues.put(KEY_PELANGGAN_EMAIL, email);
        contentValues.put(KEY_PELANGGAN_HP, hp);

        // Update the row where the id matches
        int result = db.update(TABLE_PELANGGAN, contentValues, KEY_PELANGGAN_ID + " = ?", new String[]{id});
        db.close();
        return result > 0; // Mengembalikan true jika update berhasil
    }

    // Fungsi untuk menghapus pelanggan
    public boolean deletePelanggan(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PELANGGAN, KEY_PELANGGAN_ID + " = ?", new String[]{id});
        db.close();
        return result > 0; // Mengembalikan true jika penghapusan berhasil
    }
}
