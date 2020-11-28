package com.example.sapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        boolean wut = false;
        boolean start = getIntent().getBooleanExtra("start", wut);
    }

    public void Click(View view) {
        //Создаем переход:
        Intent intent=new Intent(MenuActivity.this,SapperMain.class);
        //Запускаем его при нажатии:
        startActivity(intent);
    }
}