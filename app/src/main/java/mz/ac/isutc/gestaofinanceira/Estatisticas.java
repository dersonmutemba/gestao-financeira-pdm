////package mz.ac.isutc.gestaofinanceira;
////
////import android.os.Bundle;
////
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.fragment.app.Fragment;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.viewpager.widget.ViewPager;
////
////import android.text.Editable;
////import android.text.TextWatcher;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.Toast;
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class Estatisticas extends Fragment {
////
////    ViewPager viewPager;
////
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
////    }
////
////
////    @Override
////    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
////        super.onViewCreated(view, savedInstanceState);
////
////        viewPager = getView().findViewById(R.id.viewPagerId);
////
////        Fragment ativos = Fragment.instantiate(getContext(),ActivosFragment.class.getName());
////        Fragment passivos = Fragment.instantiate(getContext(), PassivosFragment.class.getName());
////
////        List<Fragment> fragmentList = new ArrayList<Fragment>();
////
////        fragmentList.add(ativos);
////        fragmentList.add(passivos);
////
////        VPAdapter adapter = new VPAdapter(getActivity().getSupportFragmentManager(),fragmentList);
////
////        viewPager.setAdapter(adapter);
////    }
////}
//package mz.ac.isutc.gestaofinanceira;
//
//import android.graphics.Color;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.viewpager.widget.ViewPager;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.utils.ColorTemplate;
//import com.google.android.material.tabs.TabLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Estatisticas extends Fragment {
//
//    ViewPager viewPager;
//    // variable for our bar chart
//    BarChart barChart;
//
//    // variable for our bar data.
//    BarData barData;
//
//    // variable for our bar data set.
//    BarDataSet barDataSet;
//
//    // array list for storing entries.
//    ArrayList barEntriesArrayList;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_activos, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        viewPager = getView().findViewById(R.id.viewPagerId);
//
//        List<Fragment> fragmentList = new ArrayList<>();
//
//        fragmentList.add(new ActivosFragment());
//        fragmentList.add(new PassivosFragment());
//
//        TabLayout tabLayout;
//        tabLayout = view.findViewById(R.id.tabLayout);
//
//
//        VPAdapter adapter = new VPAdapter(getActivity().getSupportFragmentManager(), fragmentList);
//
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        viewPager.setCurrentItem(1);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//}

package mz.ac.isutc.gestaofinanceira;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class Estatisticas extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        tabLayout  = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPagerId);

        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpager = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpager.addFragment(new ActivosFragment(), "Activos");
        vpager.addFragment(new PassivosFragment(), "Passivos");
        viewPager.setAdapter(vpager);
    }



}