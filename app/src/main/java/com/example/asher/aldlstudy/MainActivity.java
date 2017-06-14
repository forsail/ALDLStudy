package com.example.asher.aldlstudy;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvBind;

    private BookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBind = (TextView) findViewById(R.id.tvBind);
        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AIDLService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        bookManager = BookManager.Stub.asInterface(service);
                        List<Book> books = null;
                        try {
                            books = bookManager.getBooks();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if (books != null) {
                            for (Book book : books
                                    ) {
                                Log.d(TAG, "bookName:" + book.getName() + " bookPrice:" + book.getPrice());
                            }

                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Service.BIND_AUTO_CREATE);
            }
        });
    }
}
