package com.example.admin.ch8_3_3;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressDialogFragment extends DialogFragment {
    private ProgressDialog pDialog;

    static ProgressDialogFragment newInstance(String title){
        ProgressDialogFragment dlg = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        dlg.setArguments(args);                                         //設定參數
        return dlg;
    }

    DialogInterface.OnClickListener p_click = new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int which){
            ((MainActivity)getActivity()).btn_progress_do_P_Click();
        }
    };

    DialogInterface.OnClickListener n_click = new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int which){
            ((MainActivity)getActivity()).btn_progress_do_N_Click();
        }
    };

    public Dialog onCreateDialog(Bundle savedInstanceState){
        String title = getArguments().getString("title");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle(title);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setProgress(0);
        pDialog.setButton(DialogInterface.BUTTON_POSITIVE, "隱藏",p_click);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",n_click);
        return pDialog;
    }
    // 更新 ProgressDialog 顯示的進度，每呼叫1次 就加1
    public void updateProgress(){
        pDialog.incrementProgressBy(1);
    }

    public ProgressDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false);
    }

}
