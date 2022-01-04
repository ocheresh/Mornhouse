package com.ocheresh.mornhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    MornRoomDatabase morndatabase;
    List<Mornhouse> mornhouse;
    Intent intent;
    StateAdapter adapter;


    Button butFact;
    Button butRand;
    EditText textnumber;

    Retrofit retrofit;
    MessagesApi messagesApi;
    private static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butFact = (Button) findViewById(R.id.buttonFact);
        butRand = (Button) findViewById(R.id.buttonRand);
        textnumber = (EditText)findViewById(R.id.editTextNumber);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        setInitialData();
        Log.e("ExitonResponse ", Integer.toString(mornhouse.size()));
        RecyclerView recycler = findViewById(R.id.list);
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Mornhouse state, int position) {

                    intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("name", state.getInfo());
                    startActivity(intent);

            }
        };
        adapter = new StateAdapter(this, mornhouse, stateClickListener);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        OnClickListener BtnFact = new OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    messagesApi = retrofit.create(MessagesApi.class);

                    Call<String> messages = messagesApi.messages(textnumber.getText().toString());
                    messages.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            answer = response.body();
                            morndatabase.mornDao().insertMornhouse(new Mornhouse((mornhouse.size()+1), answer));
                            mornhouse = morndatabase.mornDao().getMornhouseList();
                            adapter.updatwRecyclerView(mornhouse);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("Error: ", t.getMessage());
                        }
                    });
                }
                catch(Exception e) {
                    Log.e("Error: ", e.getMessage().toString());
                }
            }
        };
        butFact.setOnClickListener(BtnFact);

        OnClickListener BtnRand = new OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    messagesApi = retrofit.create(MessagesApi.class);

                    Call<String> messages = messagesApi.messages();
                    messages.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            answer = response.body();
                            morndatabase.mornDao().insertMornhouse(new Mornhouse((mornhouse.size()+1), answer));
                            mornhouse = morndatabase.mornDao().getMornhouseList();
                            adapter.updatwRecyclerView(mornhouse);
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("Error: ", t.getMessage());
                        }
                    });
                }
                catch(Exception e) {
                    Log.e("Error: ", e.getMessage().toString());
                }
            }
        };
        butRand.setOnClickListener(BtnRand);
    }

    private void setInitialData(){
        morndatabase = MornRoomDatabase.getInstance(this);
        mornhouse = morndatabase.mornDao().getMornhouseList();
    }
}