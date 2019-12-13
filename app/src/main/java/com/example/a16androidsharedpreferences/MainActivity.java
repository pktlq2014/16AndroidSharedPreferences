package com.example.a16androidsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText editText1, editText2;
    CheckBox checkBox1;
    Button butTon1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE);
        anhXa();
        // gán giá trị vào edittext nếu có checked vào lần sau
        editText1.setText(sharedPreferences.getString("taikhoan", ""));
        editText2.setText(sharedPreferences.getString("matkhau", ""));
        checkBox1.setChecked(sharedPreferences.getBoolean("checked", false));
        butTon1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String a = editText1.getText().toString();
                String b = editText2.getText().toString();
                if(a.equals("vanty") && b.equals("123456"))
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // nếu có check
                    if(checkBox1.isChecked())
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", a);
                        editor.putString("matkhau", b);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bạim ", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("taikhoan");
                    editor.remove("matkhau");
                    editor.remove("checked");
                    editor.commit();
                }
            }
        });
    }
    private void anhXa()
    {
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        checkBox1 = findViewById(R.id.checkBox1);
        butTon1 = findViewById(R.id.butTon1);
    }
}
