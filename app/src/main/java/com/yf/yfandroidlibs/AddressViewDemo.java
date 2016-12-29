package com.yf.yfandroidlibs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.github.yf_library.dw.AddressView;

public class AddressViewDemo extends Activity {

    private AddressView address_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_view_demo);
        address_view=(AddressView)this.findViewById(R.id.address_view);
        address_view.setAddressData(getPackageName());

        Log.i("mylog", "包名"+getPackageName());
    }
}
