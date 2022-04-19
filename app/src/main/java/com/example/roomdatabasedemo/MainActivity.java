package com.example.roomdatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase mDb;
    private List<User> listUser;
    private ListView listView;
    private Button btnAdd;
    private Button btnDelete;
    private TextView txtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        btnAdd = (Button) findViewById(R.id.button);
        btnDelete = (Button) findViewById(R.id.button2);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //get value in textAdd
                txtAdd = findViewById(R.id.editTextTextPersonName);
                String value =  txtAdd.getText().toString();
                if(value.length()!=0){
                    User u = new User(value);

                    mDb.userDao().insertUser(u);
                    txtAdd.setText("");

                    listUser = renderData();
                    Toast.makeText( MainActivity.this,String.valueOf(listUser.size()) , Toast.LENGTH_LONG).show();

                    Toast.makeText( MainActivity.this,"Add Succesfully!" , Toast.LENGTH_LONG).show();
//                    //render
                    UserAdaper adapter = new UserAdaper(MainActivity.this
                            , R.layout.activity_item, listUser);
                    listView.setAdapter(adapter);

                    txtAdd.requestFocus();
                }
                else{
                    Toast.makeText( MainActivity.this,"Add faild!" , Toast.LENGTH_LONG).show();
                }
            }
        });

        listView = (ListView) findViewById(R.id.mobile_list);
        listUser = renderData();
        Toast.makeText( MainActivity.this,String.valueOf(listUser.size()) , Toast.LENGTH_LONG).show();
        UserAdaper adapter = new UserAdaper(this, R.layout.activity_item, listUser);
        listView.setAdapter(adapter);

    }


    //render list user into array
    public List<User> renderData(){
        // Reading all contacts
        List<User> list = new ArrayList<User>();
        List<User> contacts = mDb.userDao().findAllUserSync();
        for (User cn : contacts) {
            list.add(cn);
        }

        return list;
    }
}