package com.example.android.myweb;

import android.app.DownloadManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.myweb.NewTab.NewTab1;
import com.example.android.myweb.PrivateTab.PrivateTab1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.lang.reflect.Method;

public class Begin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    MaterialSearchView materialSearchView;
    DrawerLayout drawer ;
    WebView webView;
    EditText editText;
    ProgressBar progressBar;
    String searchEngine;
    LinearLayout linearLayout;
    String load;
    Button b1,b2,b3,b4,b5,b6,b7;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Don");
    String[] list;
    String book;
    int i=1;
    private GestureDetectorCompat mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    int REQUEST_CODE=1;

     //   ActivityCompat.requestPermissions(this, new String[]{
     //           Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        progressBar = findViewById(R.id.pro);
        editText=findViewById(R.id.find);
        b6=findViewById(R.id.butt);
        b7=findViewById(R.id.cross);
        linearLayout = findViewById(R.id.iAmLinear);
        searchEngine="https://www.google.com/search?q=";
        list=new String[]{"google"};
        mDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

         /*  databaseExpenses.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot myPlacesSnapShot: dataSnapshot.getChildren())
                    {
                        UUrl myPlaces = myPlacesSnapShot.getValue(UUrl.class);
                        if(myPlaces!=null && myPlaces.getUrl()!=null)
                        {
                            list[i]=myPlaces.getUrl();
                            i++;
                        }
                        else
                        {
                            Toast.makeText(Begin.this,"NULLLLLLLL",Toast.LENGTH_SHORT).show();
                        }
                        if(i>=1000)
                            break;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //   list.clear();
                }
            });*/
        b1=findViewById(R.id.gmail);
        b2=findViewById(R.id.google);
        b3=findViewById(R.id.youtube);
        b4=findViewById(R.id.amazon);
        b5=findViewById(R.id.code_forces);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialSearchView=findViewById(R.id.mySearch);
        materialSearchView.closeSearch();
        webView = findViewById(R.id.webs);
        webView.setWebViewClient(new myWebViewClient());
        Intent intent = getIntent();
        load = new String("nothing");
        load = intent.getStringExtra("load");
        if(load!=null && load.equals("nothing"))
        {
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            progressBar.setVisibility(View.VISIBLE);
            webView.loadUrl(searchEngine+load);
            progressBar.setVisibility(View.GONE);
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                String id = databaseExpenses.push().getKey();
                UUrl url =new UUrl(query);
                databaseExpenses.child("Hippy").child("Bro").setValue(url);
            //    Intent intent = new Intent(Begin.this,MainActivity.class);
            //    intent.putExtra("key_1",query);
            //    startActivity(intent);
            //    ProgressBar progressBar = findViewById(R.id.pro);
            //    progressBar.setVisibility(View.VISIBLE);
                book=searchEngine+query;
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(searchEngine+query);
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseExpenses.push().getKey();
                BookMark bookMark =new BookMark(book);
                databaseExpenses.child(id).setValue(bookMark);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://gmail.com");
                progressBar.setVisibility(View.GONE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://google.com");
                progressBar.setVisibility(View.GONE);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://youtube.com");
                progressBar.setVisibility(View.GONE);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://amazon.com");
                progressBar.setVisibility(View.GONE);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://codeforces.com");
                progressBar.setVisibility(View.GONE);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b6.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                b7.setVisibility(View.GONE);
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(s));
                myRequest.allowScanningByMediaScanner();
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
          //    myRequest.setDestinationInExternalPublicDir("/DOWNLOADS","file.pdf");
                DownloadManager manager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(myRequest);

                Toast.makeText(Begin.this,"Downloading...",Toast.LENGTH_LONG).show();
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        databaseExpenses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot myPlacesSnapShot: dataSnapshot.getChildren())
                {
                    MySearch myPlaces = myPlacesSnapShot.getValue(MySearch.class);
                    if(myPlaces!=null && myPlaces.getSearch()!=null)
                    {
                        searchEngine=myPlaces.getSearch();
                    }
                    else
                    {
                      //  Toast.makeText(Begin.this,"NULLLLLLLL",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //   MyPlacesList.clear();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

 //   @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
 //       // Inflate the menu; this adds items to the action bar if it is present.
 //       getMenuInflater().inflate(R.menu.begin, menu);
 //       return true;
 //   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.bookmarks)
        {
         //   Toast.makeText(getApplicationContext(),"Hiiiiiiiiiiiiiiiiii",Toast.LENGTH_LONG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.top:
                webView.scrollTo(0,0);
                break;
            case R.id.refresh:
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                webView.loadUrl("https://"+book+".com");
                progressBar.setVisibility(View.GONE);
                break;
            case R.id.history:
                Toast.makeText(getApplicationContext(),"History",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Begin.this,History.class);
                startActivity(intent);
                break;
            case R.id.new_tab:
                Intent intent1 = new Intent(Begin.this, NewTab1.class);
                intent1.putExtra("tab_1",book);
                intent1.putExtra("searchE",searchEngine);
                startActivity(intent1);
                break;
            case R.id.new_private_tab:
                Intent intent2 = new Intent(Begin.this, PrivateTab1.class);
                startActivity(intent2);
                break;
            case R.id.bookmarks:
                Intent intent3 = new Intent(Begin.this,BookMarks.class);
                startActivity(intent3);
                break;
            case R.id.connect:
                Intent intent4 = new Intent(Begin.this,Connect.class);
                startActivity(intent4);
                break;
            case R.id.settings:
                Intent intent5 = new Intent(Begin.this,Setting.class);
                startActivity(intent5);
                break;
            case R.id.share:
                Intent intent6 = new Intent(Begin.this,Share.class);
                intent6.putExtra("key_1",book);
                startActivity(intent6);
                break;
            case R.id.searchInPage:
            {
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                b6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //    editText.setVisibility(View.VISIBLE);
                        //    b6.setVisibility(View.VISIBLE);
                        String s=editText.getText().toString();
                        if(s!=null)
                        {
                            webView.findAll(s);
                            try {
                                for (Method m : WebView.class.getDeclaredMethods()) {
                                    if (m.getName().equals("setFindIsUp")) {
                                        m.setAccessible(true);
                                        m.invoke((webView), true);
                                        break;
                                    }
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                });
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Toast.makeText(Begin.this,"DoubleTap",Toast.LENGTH_LONG).show();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        webView.loadUrl(book);
        progressBar.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Toast.makeText(Begin.this,"DoubleTapEvent",Toast.LENGTH_LONG).show();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        webView.loadUrl(book);
        progressBar.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        MenuItem item1 = menu.findItem(R.id.all_tabs);
        materialSearchView.setMenuItem(item);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(Begin.this,AllTabs.class);
                intent.putExtra("tab_1",book);
                intent.putExtra("tab_2","nothing");
                intent.putExtra("tab_3","nothing");
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

}
