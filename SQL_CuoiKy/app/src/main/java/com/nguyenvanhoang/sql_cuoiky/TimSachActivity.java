package com.nguyenvanhoang.sql_cuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TimSachActivity extends AppCompatActivity {
    private Button btnThoat,btnTimKiem;
    private EditText edtMaSach;
    private GridView gridView;
    private DbHelper dbHelper;
    private List<String> sachList =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_sach);
        connectView();
        dbHelper = new DbHelper(this);
        for(Sach s : dbHelper.getALLSach()){
            sachList.add(s.getMaSach() + "");
            sachList.add(s.getTenSach());
            sachList.add(s.getTenTacGia());
        };
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sachList);
        gridView.setAdapter(adapter);
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMaSach.getText().toString().trim().equals("")){
                    sachList.clear();
                    for(Sach s : dbHelper.getALLSach()){
                        sachList.add(s.getMaSach() + "");
                        sachList.add(s.getTenSach());
                        sachList.add(s.getTenTacGia());
                    };
                    adapter.notifyDataSetChanged();
                }
                else{
                    sachList.clear();
                    Sach s = dbHelper.getSachByMaSach(Integer.parseInt(edtMaSach.getText().toString().trim()));
                    sachList.add(s.getMaSach() + "");
                    sachList.add(s.getTenSach());
                    sachList.add(s.getTenTacGia());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(TimSachActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có chắc chắn muốn thoát???");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    public void connectView(){
        btnThoat = (Button) findViewById(R.id.btnThoat);
        btnTimKiem = (Button) findViewById(R.id.btnTimKiem);
        edtMaSach =(EditText) findViewById(R.id.edtMaSach);
        gridView = (GridView) findViewById(R.id.gvTimKiem);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id  =item.getItemId();
        switch (id){
            case R.id.mnThemSach:
                startActivity(new Intent(TimSachActivity.this,ThemSachActivity.class));
                break;
            case R.id.mnTimSach:
                startActivity(new Intent(TimSachActivity.this,TimSachActivity.class));
                break;
            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(TimSachActivity.this);
                builder.setTitle("Xác nhận thoát???");
                builder.setMessage("Bạn có chăc chắn muốn thoát?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}