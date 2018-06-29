package appnfc.hongye.com.scan_crat_qr_bar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.hylg.scancode.activity_scan.CommonScanActivity;
import com.hylg.scancode.activity_scan.CreateCodeActivity;
import com.hylg.scancode.utils.Constant;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create_code).setOnClickListener(this);
        findViewById(R.id.create_qr_code).setOnClickListener(this);
        findViewById(R.id.create_zxing_code).setOnClickListener(this);
        findViewById(R.id.scan_2code).setOnClickListener(this);
        findViewById(R.id.scan_bar_code).setOnClickListener(this);
        findViewById(R.id.scan_code).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tv_mesg);
        testData();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.create_code: //生成码
                intent = new Intent(this, CreateCodeActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                intent.putExtra(Constant.CREAL_CODE_KEY, "1234567890CSDCECECECSC");
                startActivity(intent);
                break;
            case R.id.create_qr_code:
                intent = new Intent(this, CreateCodeActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
                intent.putExtra(Constant.CREAL_CODE_KEY, "1234567890CSDCECECECSC");
                startActivity(intent);
                break;
            case R.id.create_zxing_code:
                intent = new Intent(this, CreateCodeActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_BARCODE_MODE);
                intent.putExtra(Constant.CREAL_CODE_KEY, "1234567890CSDCECECECSC");
                startActivity(intent);
                break;
            case R.id.scan_2code: //扫描二维码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
                intent.putExtra(Constant.REQUEST_SCAN_TYPE, Constant.REQUEST_SCAN_TYPE_REGIST);
                startActivityForResult(intent, Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
                break;

            case R.id.scan_bar_code://扫描条形码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_BARCODE_MODE);
                intent.putExtra(Constant.REQUEST_SCAN_TYPE, Constant.REQUEST_SCAN_TYPE_COMMON);
                startActivityForResult(intent, Constant.REQUEST_SCAN_MODE_BARCODE_MODE);
                break;

            case R.id.scan_code://扫描条形码或者二维码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                intent.putExtra(Constant.REQUEST_SCAN_TYPE, Constant.REQUEST_SCAN_TYPE_REGIST_AUTO);
                startActivityForResult(intent, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentDta) {
        super.onActivityResult(requestCode, resultCode, intentDta);
        if (intentDta != null) {
            String extra = intentDta.getStringExtra(Constant.REQUEST_SCAN_MODE);
            textView.setText(extra);
            System.out.println("扫码测试===========data==" + extra);
        }
    }

    /**
     * 测试扫描数据
     */
    private void testData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (Constant.REQUEST_SCAN_MODE_ALL_DATA != null) {
                            System.out.println("扫码测试=====001===" + Constant.REQUEST_SCAN_MODE_ALL_DATA);
                            Constant.REQUEST_SCAN_MODE_ALL_DATA = null;
                        }
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
