package com.example.a16androidsharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    Button butTon1, butTon2, butTon3;
    SharedPreferences sharedPreferences;

    String taiKhoanDangKy, matKhauDangKy;
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
                if(editText1.getText().length() != 0 && editText2.getText().length() != 0)
                {
                    if(a.equals(taiKhoanDangKy) && b.equals(matKhauDangKy))
                    {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
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
                    else if(a.equals("vanty") && b.equals("123456"))
                    {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Mời Bạn Nhập Đầy Đủ Thông Tin!!!", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("taikhoan");
                    editor.remove("matkhau");
                    editor.remove("checked");
                    editor.commit();
                }
            }
        });
        butTon3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn Có Chắc Chắn Muốn Thoát!");
                builder.setMessage("Hãy Lựa Chọn Xác Nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        butTon2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Hộp Thoại Xử Lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog);


                final EditText editText3 = dialog.findViewById(R.id.editText3);
                final EditText editText4 = dialog.findViewById(R.id.editText4);
                Button button4 = dialog.findViewById(R.id.butTon4);
                Button button5 = dialog.findViewById(R.id.butTon5);
                button5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        taiKhoanDangKy = editText3.getText().toString().trim();
                        matKhauDangKy = editText4.getText().toString().trim();

                        editText1.setText(taiKhoanDangKy);
                        editText2.setText(matKhauDangKy);

                        dialog.cancel();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }
    private void anhXa()
    {
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        checkBox1 = findViewById(R.id.checkBox1);
        butTon1 = findViewById(R.id.butTon1);
        butTon2 = findViewById(R.id.butTon2);
        butTon3 = findViewById(R.id.butTon3);
    }
}
