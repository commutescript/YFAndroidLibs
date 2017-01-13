package com.github.yf_library.dialog;

import com.github.yf_library.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 类功能：
 * Created by lenovo on 2016/11/18 15:21.
 */
public class DialogView extends Dialog {
    public DialogView(Context context) {
        super(context);
    }

    public DialogView(Context context, int theme) {
        super(context, theme);
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.cutom_dialog);
//    }


    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String hint;

        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private String titleTxt01="";
        private OnOKListener mOKListener;

        public interface OnOKListener {
            public void getDialogValue01(String str);
            public void getDialogValue02(String str);

        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context,OnOKListener onOKListener) {
            this.context = context;
            this.mOKListener=onOKListener;
        }

        public Builder setMessage(String message) {
        this.message = message;
        return this;
       }

    public Builder setHint01(String message) {
        this.message = message;
        return this;
    }
    public Builder setHint02(String message) {
        this.hint = message;
        return this;
    }
    public String getHint01() {
        if(titleTxt01!=""){
            return titleTxt01;
        }

        return "";
    }
    public Builder getHint02() {
        return this;
    }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }


        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public DialogView create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.dialog_view_yfdef, null);
            final DialogView dialog = new DialogView(context,R.style.Dialog);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    250, LinearLayout.LayoutParams.WRAP_CONTENT));
            //标题
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
//                    ((Button) layout.findViewById(R.id.positiveButton)).setOnClickListener(new buttomCilck(positiveButtonClickListener,txt));
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    mOKListener.getDialogValue01(((TextView) layout.findViewById(R.id.message)).getText().toString());
                                    mOKListener.getDialogValue02(((TextView) layout.findViewById(R.id.message02)).getText().toString());
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);

                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (message != null) {
//                ((TextView) layout.findViewById(R.id.message)).setText(message);
                ((TextView) layout.findViewById(R.id.message)).setHint(message);

//                ((TextView) layout.findViewById(R.id.message02)).setText(hint);
                ((TextView) layout.findViewById(R.id.message02)).setHint(hint);
                ((TextView) layout.findViewById(R.id.message02)).getText();


            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .removeAllViews();
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
            }else if(((TextView) layout.findViewById(R.id.message02)).getText().toString().trim()!=null){
                titleTxt01=((TextView) layout.findViewById(R.id.message02)).getText().toString().trim();
            }


            dialog.setContentView(layout);
            return dialog;
        }
//    public class buttomCilck implements View.OnClickListener{
//        String msg;
//
//        public buttomCilck(DialogInterface.OnClickListener positiveButtonClickListener,String msg) {
//            this.msg=msg;
//
//        }
//
//        @Override
//        public void onClick(View view) {
//            positiveButtonClickListener.onClick(dialog,
//                    DialogInterface.BUTTON_POSITIVE);
//
//        }
//
//        public String getTxt(){
//
//            return  msg;
//        }
//    }

    }


}
