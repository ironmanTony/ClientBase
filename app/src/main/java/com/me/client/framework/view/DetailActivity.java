package com.me.client.framework.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.me.client.R;
import com.me.client.framework.bean.Book;
import com.me.client.framework.App;

public class DetailActivity extends Activity {

    public static final String BOOK_DETAIL = "bookDetail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra(BOOK_DETAIL);
        initView(book);
    }

    private void initView(Book book) {
        //init actionbar
        ActionBar actionBar = getActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //init view
        TextView tvTitle = (TextView) findViewById(R.id.detail_book_title);
        TextView tvBookId = (TextView) findViewById(R.id.detail_book_id);
        TextView tvAuthor = (TextView) findViewById(R.id.detail_book_author);
        TextView tvPublisher = (TextView) findViewById(R.id.detail_book_publisher);
        TextView tvPlace = (TextView) findViewById(R.id.detail_book_place);
        TextView tvStatus = (TextView) findViewById(R.id.detail_book_status);
        TextView tvSummary = (TextView) findViewById(R.id.detail_book_summary);

        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.detail_book_img);

        if(book!=null){
            if(book.imgSrc!=null&&!"".equals(book.imgSrc)&&book.imgSrc.contains("http")){
                imageView.setImageUrl(book.imgSrc, App.getInstance().getImageLoader());
            }else{
                imageView.setImageUrl("", App.getInstance().getImageLoader());
            }
            imageView.setDefaultImageResId(R.drawable.nobook);
            tvTitle.setText(book.name);
            tvBookId.setText("索书号："+book.bookId);
            tvAuthor.setText(book.editor==null?"":"作者："+book.editor);
            tvPublisher.setText(book.publishingHouse==null?"":"出版社："+book.publishingHouse);
            tvPlace.setText("馆藏地点："+book.place);
            tvStatus.setText(book.status==null?"":"书本状态："+book.status);
            if(book.summary!=null){
                Log.d("summary", book.summary);
                tvSummary.setText(book.summary.replaceAll("<br />", "\n"));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
