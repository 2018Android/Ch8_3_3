package com.example.admin.ch8_3_3;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerFunction();
        Button btn_progress = (Button)findViewById(R.id.btn_progress);
        btn_progress.setOnClickListener(btn_progress_Click);
    }

    //---------------------------Progress Fragment-------------------------
    public void btn_progress_do_P_Click(){
        Toast.makeText(this, "隱藏下載對話方塊", Toast.LENGTH_SHORT).show();
    }

    public void btn_progress_do_N_Click(){
        Toast.makeText(this, "取消下載", Toast.LENGTH_SHORT).show();
        finish();
    }

    private ProgressDialogFragment dlg;
    private int p=0;
    private Handler pHandler;

    private void HandlerFunction(){
        pHandler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(p>100){
                    dlg.dismiss();
                    TextView txv_progressFinish = (TextView)findViewById(R.id.txv_progressFinish);
                    txv_progressFinish.setText("下載已完成");
                }else{
                    p++;
                    dlg.updateProgress();
                    pHandler.sendEmptyMessageDelayed(0, 50);
                }
            }
        };
    }

    View.OnClickListener btn_progress_Click = new View.OnClickListener(){
        public void onClick(View v){
            dlg = ProgressDialogFragment.newInstance("下載檔案");
            FragmentManager fm = getSupportFragmentManager();
            dlg.show(fm, "progressdialog");
            p=0;
            pHandler.sendEmptyMessage(0);
        }
    };
}
