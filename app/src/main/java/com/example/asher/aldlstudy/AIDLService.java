package com.example.asher.aldlstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihong on 2017/6/14.
 */

public class AIDLService extends Service {
    private static final String TAG = "AIDLService";

    private List<Book> books = new ArrayList<>();

    private BookManager.Stub bookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book("java", 100);
        books.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
