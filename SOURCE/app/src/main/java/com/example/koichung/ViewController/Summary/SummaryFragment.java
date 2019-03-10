package com.example.koichung.ViewController.Summary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.koichung.LoginActivity;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.ViewController.Base.BaseFragment;

public class SummaryFragment extends BaseFragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_summary,container,false);
        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_summary,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mnu_logout){
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("Cảnh báo");
            builder.setIcon(R.drawable.ic_warning);
            builder.setMessage("Bạn có chắc muốn đăng xuất không");
            builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AppConfig.setUserID(getActivity(),-1);
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return true;
    }
}
