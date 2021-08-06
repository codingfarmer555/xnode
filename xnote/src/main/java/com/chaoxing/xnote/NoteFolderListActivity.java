package com.chaoxing.xnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaoxing.xnote.domain.NoteFolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NoteFolderListActivity extends AppCompatActivity {

    //文件夹列表
    private static  ArrayList<NoteFolder> noteFolders=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_folder_list);

        //文件夹listview
        ListView lv_folder=findViewById(R.id.lv_folder);


        //文件夹json数据
        String noteFolder="[" +
                "{'folderName':'公开笔记','isOpen':'1','noteCount':3}," +
                "{'folderName':'私有笔记','isOpen':'0','noteCount':1}," +
                "{'folderName':'工作日志','isOpen':'0','noteCount':5}," +
                "{'folderName':'新建文件夹','isOpen':'0','noteCount':2}]";

        Gson gson = new Gson();

        Type noteFoldersListType = new TypeToken<ArrayList<NoteFolder>>(){}.getType();

        noteFolders = gson.fromJson(noteFolder, noteFoldersListType);


        lv_folder.setAdapter(new NoteFolderAdapter());


        lv_folder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //条目点击事件，跳转到笔记列表
                Intent intent = new Intent(getApplicationContext(), NoteListActivity.class);

                intent.putExtra("folderId",position);
                intent.putExtra("folderName",noteFolders.get(position).getFolderName());
                intent.putExtra("isOpen",noteFolders.get(position).getIsOpen());
                intent.putExtra("count",noteFolders.get(position).getNoteCount());
                startActivity(intent);
            }
        });
    }

    private class NoteFolderAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return noteFolders.size();
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
                //实例 文件夹条目
                itemView = View.inflate(NoteFolderListActivity.this, R.layout.item_note_folder, null);

            } else {
                itemView = convertView;
            }

            //找控件  文件夹名称 是否私有  笔记数量 文件夹图标
            TextView  tv_folderName    =(TextView)itemView.findViewById(R.id.tv_folderName);
            TextView  tv_isOpen        =(TextView)itemView.findViewById(R.id.tv_isOpen);
            TextView  tv_noteCount     =(TextView)itemView.findViewById(R.id.tv_noteCount);
            ImageView img_icon_folder  =(ImageView)itemView.findViewById(R.id.img_icon_folder);

            String  folderName=noteFolders.get(position).getFolderName();

            //根据isOpen设置图标
            if (noteFolders.get(position).getIsOpen().equals("0")){
                img_icon_folder.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_folder));
//                img_icon_folder.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_folder_open));
            }else{
                img_icon_folder.setImageResource(R.drawable.icon_folder_open);
            }

            String isOpen = noteFolders.get(position).getIsOpen().equals("0") ? "私有" : "公开";
            String noteCount = noteFolders.get(position).getNoteCount() + "";


            tv_folderName.setText(folderName);
            tv_isOpen.setText(isOpen);
            tv_noteCount.setText(noteCount);


            return itemView;


        }

    }
}