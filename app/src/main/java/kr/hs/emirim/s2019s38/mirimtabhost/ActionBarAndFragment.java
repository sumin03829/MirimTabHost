package kr.hs.emirim.s2019s38.mirimtabhost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ActionBarAndFragment extends AppCompatActivity {
    ActionBar.Tab tabPark,tabSon,tabAhn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar=getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabPark=bar.newTab();
        tabPark.setText("박세리");
        tabPark.setTabListener(tabListener);
        bar.addTab(tabPark);

        tabSon=bar.newTab();
        tabSon.setText("손예진");
        tabSon.setTabListener(tabListener);
        bar.addTab(tabSon);

        tabAhn=bar.newTab();
        tabAhn.setText("안현모");
        tabAhn.setTabListener(tabListener);
        bar.addTab(tabAhn);
    }
    MyFragment myFrags[]=new MyFragment[3];

    ActionBar.TabListener tabListener=new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            MyFragment myFrag=null;
            if(myFrags[tab.getPosition()]==null){
                myFrag=new MyFragment();
                Bundle data=new Bundle();
                data.putString("tabName",tab.getText().toString());
                myFrag.setArguments(data);
                myFrags[tab.getPosition()]=myFrag;
            }else{
                myFrag=myFrags[tab.getPosition()];
            }
            ft.replace(android.R.id.content,myFrag);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };

    public static class MyFragment extends Fragment{
        String tabName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data=getArguments();
            tabName=data.getString("tabName");
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout linear=new LinearLayout(super.getActivity());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            linear.setLayoutParams(params);
            linear.setOrientation(LinearLayout.VERTICAL);
            if(tabName.equals("박세리"))
                linear.setBackgroundColor(Color.RED);
            if(tabName.equals("손예진"))
                linear.setBackgroundColor(Color.YELLOW);
            if(tabName.equals("안현무"))
                linear.setBackgroundColor(Color.BLUE);
            return linear;
        }
    }
}