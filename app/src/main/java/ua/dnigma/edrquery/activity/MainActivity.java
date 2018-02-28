package ua.dnigma.edrquery.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.adapter.ViewPagerAdapter;
import ua.dnigma.edrquery.callback.OnEdrQueryCallback;
import ua.dnigma.edrquery.fragment.EdrFavoriteFragment;
import ua.dnigma.edrquery.fragment.EdrQueryFragment;
import ua.dnigma.edrquery.manager.EdrQueryManager;
import ua.dnigma.edrquery.model.EdrQuery;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private WebView webViewDeclaration;
    Context context = MainActivity.this;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        webViewDeclaration = findViewById(R.id.webview_declaration);
        webViewDeclaration.getSettings().setJavaScriptEnabled(true);
        webViewDeclaration.setVisibility(View.INVISIBLE);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    viewPager.getAdapter().notifyDataSetChanged();
                } if (position == 0) {
                    Toast.makeText(context, R.string.search, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, R.string.search, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edr_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.filter).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                new EdrQueryManager(context).getDeclarationAsync(new OnEdrQueryCallback() {
                    @Override
                    public void onSucsses(EdrQuery edrQuery) {
                        ((EdrQueryFragment) ((ViewPagerAdapter) viewPager.getAdapter()).getItem(0)).onSucsses(edrQuery);

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                }, s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new EdrQueryFragment(), "Декларации");
        viewPagerAdapter.addFragment(new EdrFavoriteFragment(), "Избранное");
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void setWebViewDeclaration(String url) {
        webViewDeclaration.loadUrl("http://docs.google.com/viewer?url=" + url);
        webViewDeclaration.setVisibility(View.VISIBLE);
    }

}

