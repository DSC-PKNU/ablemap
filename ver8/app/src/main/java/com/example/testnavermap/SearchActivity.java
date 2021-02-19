package com.example.testnavermap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ItemAdapter.onItemListener {

    private ItemAdapter adapter;
    private List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpRecyclerView();
    }

    /****************************************************
     리사이클러뷰, 어댑터 셋팅
     ***************************************************/
    private void setUpRecyclerView() {
        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //adapter
        itemList = new ArrayList<>(); //샘플테이터
        fillData();
        adapter = new ItemAdapter(itemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL); //밑줄
        recyclerView.addItemDecoration(dividerItemDecoration);

        //데이터셋변경시
        //adapter.dataSetChanged(exampleList);

        //어댑터의 리스너 호출
        adapter.setOnClickListener(this);
    }

    private void fillData() {
        itemList = new ArrayList<>(); //샘플테이터
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
//        itemList.add(new ItemModel("", ""));
        itemList.add(new ItemModel("웨이브온 커피", "부산광역시 기장군 장안읍 해맞이로 286"));
        itemList.add(new ItemModel("우즈베이커리", "부산광역시 기장군 일광면 이천리 132-33번지"));
        itemList.add(new ItemModel("해운대 블루라인파크", "부산광역시 해운대구 중동 달맞이길62번길 13\n"));
        itemList.add(new ItemModel("해운대 미포끝집", "부산광역시 해운대구 중동 달맞이길62번길 77"));
        itemList.add(new ItemModel("해운대 백년식당", "부산광역시 해운대구 우동 구남로 23번길"));
        itemList.add(new ItemModel("더베이 101", "부산광역시 해운대구 우동 동백로 52 더베이101요트"));
        itemList.add(new ItemModel("트릭아이 뮤지엄", "부산광역시 중구 동광동3가 대청로126번길 12 2층"));
        itemList.add(new ItemModel("부산 영화 체험 박물관", "부산광역시 중구 동광동3가 대청로126번길 12"));
        itemList.add(new ItemModel("부산 근대 역사관", "부산광역시 중구 대청동2가 대청로 104"));
        itemList.add(new ItemModel("용두산공원", "부산광역시 중구 광복동2가 용두산길 37-55"));
        itemList.add(new ItemModel("UN기념공원", "부산광역시 남구 대연동 유엔평화로 93"));
        itemList.add(new ItemModel("부산박물관", "부산광역시 남구 대연4동 유엔평화로 63"));
        itemList.add(new ItemModel("부산 시민회관", "부산광역시 동구 범일2동 자성로133번길 16"));
        itemList.add(new ItemModel("해운대 해수욕장", "부산 해운대구 우동"));
        itemList.add(new ItemModel("씨라이프 부산 아쿠아리움", "부산 해운대구 226"));
        itemList.add(new ItemModel("부산 시립미술관", "부산광역시 해운대구 우동 APEC로 58"));

    }

    /****************************************************
     onCreateOptionsMenu SearchView  기능구현
     ***************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}