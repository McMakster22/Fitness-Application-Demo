package dk.now.just.bogym;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import dk.now.just.bogym.MainActivity;
import dk.now.just.bogym.Adapter.RecyclerViewAdapter;
import dk.now.just.bogym.Model.Challenge;

public class ListChallenges extends MainActivity {
    List<Challenge> challengeList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_challenges);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.list_ch);
        adapter = new RecyclerViewAdapter(challengeList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }


    private void initData(){
        challengeList.add(new Challenge(R.drawable.plank, "Plank"));
        challengeList.add(new Challenge(R.drawable.side, "Side Plank"));
        challengeList.add(new Challenge(R.drawable.wall, "Wall Sit"));

    }
}
