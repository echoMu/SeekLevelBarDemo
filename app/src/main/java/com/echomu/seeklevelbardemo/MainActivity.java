package com.echomu.seeklevelbardemo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<LevelBean> levelBeanList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LevelLayout levelLayout=findViewById(R.id.layout_level);

        LevelBean levelBean =new LevelBean(getIntColor(R.color.ovalution_level_1),"1","阴");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_2),"2","弱阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_3),"3","弱阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_4),"4","弱阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_5),"5","弱阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_6),"6","阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_7),"7","阳");
        levelBeanList.add(levelBean);
        levelBean =new LevelBean(getIntColor(R.color.ovalution_level_8),"8","强阳");
        levelBeanList.add(levelBean);

        levelLayout.setData(levelBeanList, new OnRangeSeekBarListener() {
            @Override
            public void onSeekStop(int level,String name) {
                Toast.makeText(MainActivity.this,name+level,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int getIntColor(int color){
        return ContextCompat.getColor(this,color);
    }
}
