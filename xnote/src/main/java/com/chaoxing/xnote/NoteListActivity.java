package com.chaoxing.xnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chaoxing.xnote.domain.NoteItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    private int noteCount;

    private  ArrayList<NoteItem> noteList=null;

    //头像
    String avatarUrl="https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%A4%B4%E5%83%8F%E7%B4%A0%E6%9D%90/Snipaste_2021-06-22_13-48-39.jpg";

    String noteListJson= "   [" +
                         "     {'username':'张广贺','publishTime':'08-04 19:25','readCount':10,'title':'标题','content':'内容','picUrls':['https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/src%3Dhttp___www.027art.com_abc_UploadPic_image_2020_04_1588184128944275.jpg%26refer%3Dhttp___www.027art.jpg','https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/1000.jpg'],                                                                                                               'commentCount':5,'favourCount':10,'transmitCount':2},    " +
                         "     {'username':'张广贺','publishTime':'08-04 19:25','readCount':10,'title':'标题','content':'内容','picUrls':['https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/src%3Dhttp___www.027art.com_abc_UploadPic_image_2020_04_1588184128944275.jpg%26refer%3Dhttp___www.027art.jpg','https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/1000.jpg','https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/1000.jpg'], 'commentCount':5,'favourCount':10,'transmitCount':2},    " +
                         "     {'username':'张广贺','publishTime':'08-04 19:25','readCount':10,'title':'标题','content':'内容','picUrls':['https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/src%3Dhttp___www.027art.com_abc_UploadPic_image_2020_04_1588184128944275.jpg%26refer%3Dhttp___www.027art.jpg'],                                                                                                                                                                                                                             'commentCount':5,'favourCount':10,'transmitCount':2},    " +
                         "     {'username':'张广贺','publishTime':'08-04 19:25','readCount':10,'title':'标题','content':'内容','picUrls':['https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/src%3Dhttp___www.027art.com_abc_UploadPic_image_2020_04_1588184128944275.jpg%26refer%3Dhttp___www.027art.jpg','https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/1000.jpg'],                                                                                                               'commentCount':5,'favourCount':10,'transmitCount':2}"      +
                         "   ]  ";
    private String isOpen;
    private String folderName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        Intent intent = getIntent();

        folderName = intent.getStringExtra("folderName");
        isOpen = intent.getStringExtra("isOpen");
        noteCount = intent.getIntExtra("count",0);

        //返回按键
        View img_icon_noteList_back = findViewById(R.id.img_icon_noteList_back);

        //返回按键功能
        img_icon_noteList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tv_folderName  = (TextView)findViewById(R.id.tv_folderName_noteList);
        ImageView img_icon_folder=(ImageView)findViewById(R.id.img_icon_folder_noteList);

        //设置文件夹图标
        if (isOpen.equals("0")){
            img_icon_folder.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_folder));
        }else{
            img_icon_folder.setImageResource(R.drawable.icon_folder_open);

        }
        //文件夹名称
        tv_folderName.setText(folderName);

        Gson gson = new Gson();
        Type noteListType = new TypeToken<ArrayList<NoteItem>>(){}.getType();
        noteList = gson.fromJson(noteListJson, noteListType);

        ListView lv_folder_list = (ListView)findViewById(R.id.lv_folder_list);

        lv_folder_list.setAdapter(new NoteAdapter());

        //条目点击事件  进入detail
        lv_folder_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(), NoteDetailActivity.class);

                NoteItem noteItem = noteList.get(position);

                intent.putExtra("isOpen", isOpen);
                intent.putExtra("folderName",folderName);

                //传递数据,这里传一个 noteItem
                intent.putExtra("noteItem", noteItem);

                startActivity(intent);

            }
        });
    }


    private class NoteAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return noteList.size();
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

            if (convertView == null) {
                itemView = View.inflate(NoteListActivity.this, R.layout.item_note, null);

            } else {
                itemView = convertView;

            }

            //笔记信息
            ImageView img_icon_avatar =(ImageView) itemView.findViewById(R.id.img_icon_avatar);
            TextView tv_username = (TextView)itemView.findViewById(R.id.tv_username);
            TextView tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            TextView tv_readCount = (TextView)itemView.findViewById(R.id.tv_readCount);
            TextView tv_noteTitle = (TextView)itemView.findViewById(R.id.tv_noteTitle);
            TextView tv_noteContent = (TextView)itemView.findViewById(R.id.tv_noteContent);

            //三连 数量
            TextView tv_noteIssueCount = (TextView)itemView.findViewById(R.id.tv_noteIssueCount);
            TextView tv_noteFavourCount = (TextView)itemView.findViewById(R.id.tv_noteFavourCount);
            TextView tv_noteTransmitCount = (TextView)itemView.findViewById(R.id.tv_noteTransmitCount);



            //设置头像
            Glide.with(NoteListActivity.this)
                    .load(avatarUrl)
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .override(100, 100)
                    .into(img_icon_avatar);

            //设置笔记
            tv_username.setText(noteList.get(position).getUsername());
            tv_time.setText(noteList.get(position).getPublishTime());
            tv_readCount.setText(noteList.get(position).getReadCount()+"人阅读");
            tv_noteTitle.setText(noteList.get(position).getTitle());
            tv_noteContent.setText(noteList.get(position).getContent());

            //设置三连数
//            tv_noteIssueCount.setText(noteList.get(position).getCommentCount());
//            tv_noteFavourCount.setText(noteList.get(position).getFavourCount());
//            tv_noteTransmitCount.setText(noteList.get(position).getTransmitCount());

            //设置图片
//            View item_note = itemView.findViewById(R.id.pic);
            GridLayout gridLayout = (GridLayout) itemView.findViewById(R.id.pic);
            gridLayout.removeAllViews();

            /**
             * 获取图片数量，根据图片数量设置布局
             */
            int picCount = noteList.get(position).getPicUrls().length;

            //单图
            if (picCount==1){
                gridLayout.setColumnCount(1);
                gridLayout.setRowCount(1);
                ImageView imageView = new ImageView(getApplicationContext());


                Glide.with(NoteListActivity.this)
                        .load(noteList.get(position).getPicUrls()[0])
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .override(100, 100)
                        .into(imageView);

//                imageView.setImageResource(R.drawable.icon_folder);
                imageView.setPadding(2, 2, 3, 2);

                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(700, 550);

                imageView.setLayoutParams(layoutParams);

                gridLayout.addView(imageView);

                //双图
            }else if(picCount==2){
                for (int i = 0; i < 2; i++) {

                    gridLayout.setColumnCount(2);
                    gridLayout.setRowCount(2);

//                  gridLayout.setColumnCount(1);
                    gridLayout.setRowCount(1);
                    ImageView imageView = new ImageView(getApplicationContext());


                    Glide.with(NoteListActivity.this)
                            .load(noteList.get(position).getPicUrls()[i])
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .override(100, 100)
                            .into(imageView);

//                    imageView.setImageResource(R.drawable.icon_folder);
                    imageView.setPadding(2, 2, 3, 2);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(500, 480);

                    imageView.setLayoutParams(layoutParams);

                    gridLayout.addView(imageView);

                }

                //其他图片数量
            }else{
                for (int i = 0; i < picCount; i++) {
                    ImageView imageView = new ImageView(getApplicationContext());

                    Glide.with(NoteListActivity.this)
                            .load(noteList.get(position).getPicUrls()[0])
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .override(100, 100)
                            .into(imageView);

                    imageView.setPadding(2, 2, 3, 2);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(350, 288);

                    imageView.setLayoutParams(layoutParams);

                    gridLayout.addView(imageView);

                }

            }
            return itemView;
        }

    }
}