package com.study.wnw.recyclerviewrefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //添加分隔线
        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);

        //监听SwipeRefreshLayout.OnRefreshListener
        mRefreshLayout.setOnRefreshListener(this);

        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });

    }

    //初始化界面
    private void initView(){
        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.layout_swipe_refresh);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mAdapter = new MyAdapter(this,mData);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
    }

    //初始化一开始加载的数据
    private void initData(){
        mData = new ArrayList<String>();
        for (int i = 0; i < 20; i++){
            mData.add("Item"+i);
        }
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData(){
        for (int i =0; i < 10; i++){
            mData.add("嘿，我是“上拉加载”生出来的"+i);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 重写SwipeRefreshLayout.OnRefreshListener的OnRefresh方法
     * 在这里面去做下拉刷新的操作
    */
    @Override
    public void onRefresh() {
        updateData();
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    private void updateData(){
        //我在List最前面加入一条数据
        mData.add(0, "嘿，我是“下拉刷新”生出来的");
    }
}
