package com.chaoxing.xnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chaoxing.xnote.domain.NoteItem;

/**
 * 笔记detail Activity
 */
public class NoteDetailActivity extends AppCompatActivity {

    private static NoteItem noteItem=null;
    private String isOpen;
    private String folderName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_detail);

        ListView detailList = findViewById(R.id.lv_detail);

        //获取数据
        Intent intent = getIntent();
        noteItem = (NoteItem) intent.getSerializableExtra("noteItem");
        isOpen = intent.getStringExtra("isOpen");
        folderName = intent.getStringExtra("folderName");


        TextView tv_folderName_noteDetail = findViewById(R.id.tv_folderName_noteDetail);
        ImageView img_icon_folder_noteDetail = findViewById(R.id.img_icon_folder_noteDetail);

        tv_folderName_noteDetail.setText(folderName);

        //设置文件夹图标
        if (isOpen.equals("1")){
            img_icon_folder_noteDetail.setImageResource(R.drawable.icon_folder_open);
        }else{
            img_icon_folder_noteDetail.setImageResource(R.drawable.icon_folder);
        }

        //返回按键
        View img_icon_noteList_back = findViewById(R.id.img_icon_noteDetail_back);
        img_icon_noteList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置adapter
        detailList.setAdapter(new DetailListAdapter());
    }

    private class DetailListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //图片数+1, 第一条设置 除图片之外的信息
            return noteItem.getPicUrls().length+1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;


            /** 设置图片之外的信息
             * 头像*   标题
             * 名字*   内容
             * 时间*   图片
             * 阅读量* 评论
             */
            if (position==0){

                itemView = View.inflate(NoteDetailActivity.this, R.layout.item_detail_title_meta, null);
                TextView tv_username_detail    = itemView.findViewById(R.id.tv_username_detail);
                TextView tv_publishTime_detail = itemView.findViewById(R.id.tv_publishTime_detail);
                TextView tv_readCount_detail   = itemView.findViewById(R.id.tv_readCount_detail);
                TextView tv_noteContent_detail = itemView.findViewById(R.id.tv_noteContent_detail);
                TextView tv_detail_title = itemView.findViewById(R.id.tv_detail_title);

                tv_detail_title.setText(noteItem.getTitle());
                tv_username_detail.setText(noteItem.getUsername());
                tv_publishTime_detail.setText(noteItem.getPublishTime());
                tv_readCount_detail.setText("阅读"+noteItem.getReadCount() );
                tv_noteContent_detail.setText(noteItem.getContent());
                return itemView;
            }else{
                ImageView imageView = new ImageView(getApplicationContext());

                Glide.with(NoteDetailActivity.this)
                        .load(noteItem.getPicUrls()[position-1])
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .override(1000, 800)
                        .into(imageView);
                return imageView;
            }
        }
    }

}
