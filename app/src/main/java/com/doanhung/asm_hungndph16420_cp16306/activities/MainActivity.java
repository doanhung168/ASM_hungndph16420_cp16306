package com.doanhung.asm_hungndph16420_cp16306.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.doanhung.asm_hungndph16420_cp16306.fragments.GioiThieuFragment;
import com.doanhung.asm_hungndph16420_cp16306.fragments.ChiFragment;
import com.doanhung.asm_hungndph16420_cp16306.fragments.ThuFragment;
import com.doanhung.asm_hungndph16420_cp16306.fragments.ThongKeFragment;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initObject();

        // set property for toolbar
        getSupportActionBar().setTitle("Quản Lý Thu Chi");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_nav_drawer_background));

        // set toggle cho navView
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ThuFragment())
                .commit();
        // set listener cho toggle
        navView.setNavigationItemSelectedListener(this);

    }

    private void initObject() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        manager = getSupportFragmentManager();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_drawer_khoanThu:
                ThuFragment khoanThuFragment = new ThuFragment();
                toFragment(khoanThuFragment);
                closeNavDrawer();
                break;
            case R.id.nav_drawer_khoanChi:
                ChiFragment khoanChiFragment = new ChiFragment();
                toFragment(khoanChiFragment);
                closeNavDrawer();
                break;
            case R.id.nav_drawer_thongKe:
                ThongKeFragment thongKeFragment = new ThongKeFragment();
                toFragment(thongKeFragment);
                closeNavDrawer();
                break;
            case R.id.nav_drawer_gioiThieu:
                GioiThieuFragment gioiThieuFragment = new GioiThieuFragment();
                toFragment(gioiThieuFragment);
                closeNavDrawer();
                break;
            case R.id.nav_drawer_thoat:
                exitApp();
                break;
            default:
                break;
        }
        return false;
    }

    private void closeNavDrawer() {
        drawerLayout.closeDrawers();
    }

    private void exitApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát Ứng Dụng");
        builder.setMessage(R.string.thong_bao_thoat_ung_dung);
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void toFragment(Fragment fragment) {
        manager.beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        } else {
            System.exit(0);
        }
    }
}

