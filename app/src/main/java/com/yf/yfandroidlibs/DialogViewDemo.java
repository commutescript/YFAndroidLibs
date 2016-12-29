package com.yf.yfandroidlibs;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.yf_library.dialog.DialogView;

public class DialogViewDemo extends Activity {

    private Button dig_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_view_demo);

        dig_btn=(Button)this.findViewById(R.id.dig_btn);
        dig_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogView.Builder cBuilder=new DialogView.Builder(v.getContext(), new DialogView.Builder.OnOKListener() {
                    @Override
                    public void getDialogValue01(String str) {

                    }

                    @Override
                    public void getDialogValue02(String str) {

                    }
                });
                cBuilder.setTitle("测试性能").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                        .setNegativeButton("取消",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        } );
                cBuilder.setHint01("不知道你要做什么");
                cBuilder.setHint02("鬼才信你呢");
                cBuilder.create().show();
            }
        });
    }
}
