package com.mustafacan.travelmarks.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mustafacan.travelmarks.R;
import com.mustafacan.travelmarks.adapter.TravelMarkAdapter;
import com.mustafacan.travelmarks.databinding.ActivityMainBinding;
import com.mustafacan.travelmarks.model.TravelMark;
import com.mustafacan.travelmarks.roomdb.TravelMarkDao;
import com.mustafacan.travelmarks.roomdb.TravelMarkDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    TravelMarkDatabase travelMarkDatabase;
    TravelMarkDao travelMarkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        travelMarkDatabase = Room.databaseBuilder(getApplicationContext(),TravelMarkDatabase.class,"TravelMarks").build();
        travelMarkDao = travelMarkDatabase.travelMarkDao();

        compositeDisposable.add(travelMarkDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(MainActivity.this::handleResponse)
        );


    }

    private void handleResponse(List<TravelMark> travelMarkList){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TravelMarkAdapter travelMarkAdapter = new TravelMarkAdapter(travelMarkList);
        binding.recyclerView.setAdapter(travelMarkAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.travel_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_travelmark){
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}